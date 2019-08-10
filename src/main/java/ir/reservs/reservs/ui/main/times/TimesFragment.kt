package ir.reservs.reservs.ui.main.times

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.ContextThemeWrapper
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import ir.huri.jcal.JalaliCalendar
import ir.reservs.reservs.R
import ir.reservs.reservs.model.Day
import ir.reservs.reservs.model.Salon
import ir.reservs.reservs.model.Time
import ir.reservs.reservs.ui.base.BaseFragment
import ir.reservs.reservs.ui.main.MainActivity
import ir.reservs.reservs.utils.TimeUtils
import javax.inject.Inject

class TimesFragment : BaseFragment(), TimesContract.View, OnClickListener, WeekDayAdapter.OnClickListener {
    var weekDayAdapter: WeekDayAdapter? = null
        @Inject set


    var timesAdapter: TimesAdapter? = null
        @Inject set


    var timesPresenter: TimesPresenter? = null
        @Inject set

    private var timeRecyclerView: RecyclerView? = null

    private var salon: Salon? = null

    private var txtDateTitle: TextView? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_times, container, false)
    }


    override fun setup(view: View) {
        fragmentComponent?.inject(this)
        val dayRecyclerView = view.findViewById<RecyclerView>(R.id.dayRecyclerView)

        timeRecyclerView = view.findViewById(R.id.timesRecyclerView)
        txtDateTitle = view.findViewById(R.id.txtDateTitle)
        view.findViewById<ConstraintLayout>(R.id.nextConstraint).setOnClickListener {
            timesPresenter?.nextDay()
        }
        view.findViewById<ConstraintLayout>(R.id.backConstraint).setOnClickListener {
            timesPresenter?.backDay()
        }
        view.findViewById<TextView>(R.id.txtGoToday).setOnClickListener {
            timesPresenter?.selectDay(TimeUtils.getDayFromDate(JalaliCalendar()))
        }

        view.findViewById<ImageView>(R.id.imgBackTitle).setOnClickListener {
            findNavController().popBackStack()
        }
        dayRecyclerView.adapter = weekDayAdapter
        initializeStateAdapter(timeRecyclerView!!, timesAdapter!!)
        timeRecyclerView!!.adapter = getStateAdapter()
        timesPresenter?.onAttach(this)
        timesAdapter!!.setOnClickListener(this)
        weekDayAdapter?.listener = this
        salon = arguments?.getParcelable("salon")
        val date = arguments?.getString("date")
        timesPresenter?.setSalon(salon!!.id)
        timesPresenter?.goToDate(date)

        view.findViewById<TextView>(R.id.txtSalonName).text = salon?.title

    }

    override fun updateTimes(times: MutableList<Time>) {
        timesAdapter?.addTimes(times)
    }

    override fun initializeViews(days: MutableList<Day>, selectedDay: Day) {
        weekDayAdapter?.setDays(days)
        weekDayAdapter?.selectDay(selectedDay)
    }

    override fun changeSelectedDay(day: Day) {
        weekDayAdapter?.selectDay(day)
    }

    override fun updateToolbarDate(date: String) {
        txtDateTitle?.text = date
    }

    override fun clearOldTimes() {
        timesAdapter?.clearAll()
    }

    override fun click(reserve: Time) {
        val b = Bundle()
        b.putParcelable("time", reserve)
        b.putParcelable("salon", salon)
        b.putParcelable("day", weekDayAdapter?.selectedDay)
        findNavController().navigate(R.id.goToReserve, b)
    }

    override fun onClick(day: Day) {
        timesPresenter?.selectDay(day)
    }

    override fun onDestroyView() {
        timesPresenter?.onDetach()
        timesAdapter = null
        weekDayAdapter = null
        timeRecyclerView = null
        timesPresenter = null
        super.onDestroyView()
    }


}
