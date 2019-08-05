package ir.reservs.reservs.ui.main.search

import com.alirezaafkar.sundatepicker.DatePicker
import com.alirezaafkar.sundatepicker.interfaces.DateSetListener
import ir.reservs.reservs.model.City
import ir.reservs.reservs.model.Salon
import ir.reservs.reservs.utils.TimeUtils
import java.util.*


class SearchPresenter : SearchContract.Presenter, DateSetListener {

    private var view: SearchContract.View? = null
    private var city: City? = null
    private var salon: Salon? = null
    private var date: String? = null
    private var datePicker: DatePicker? = null
    override fun onAttach(view: SearchContract.View) {
        this.view = view
    }

    fun setCity(city: City) {
        this.city = city
    }

    fun setSalon(salon: Salon) {
        this.salon = salon
    }

    fun getCity() {
        view?.openCityDialog()
    }

    fun getSalon() {
        if (city == null) {
            view?.onError("لطفا ابتدا شهر مورد نظر خود را وارد نمایید.")
            return
        }
        view?.openSalonDialog(city?.id!!)
    }

    fun getDate() {
        val minDate = Calendar.getInstance()
        val maxDate = Calendar.getInstance()
        maxDate.set(Calendar.MONTH, maxDate.get(Calendar.MONTH) + 1)
        datePicker = DatePicker.Builder().id(1)
                .minDate(minDate)
                .maxDate(maxDate)
                .date(minDate)
                .build(this)
        datePicker?.show(view?.getFManager()!!, "")
    }

    override fun onDateSet(id: Int, calendar: Calendar?, day: Int, month: Int, year: Int) {
        date = TimeUtils.dateFormat(year, month, day)
        view!!.setDate(TimeUtils.dateDisplayFormat(year, month, day))
    }

    fun search() {
        if (city == null) {
            view?.onError("لطفا شهر مورد نظر خود را وارد نمایید.")
            return
        }
        if (salon == null) {
            view?.onError("لطفا مکان ورزشی مورد نظر خود را وارد نمایید.")
            return
        }
        if (date == null) {
            view?.onError("لطفا تاریخ مورد نظر خود را وارد نمایید.")
            return
        }
        view?.goToTimesFragment(salon!!, date!!)

    }

    override fun onDetach() {
        datePicker?.dismiss()
        view = null
    }

}