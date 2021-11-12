package ir.reservs.reservs.ui.main.information

import android.util.Log
import com.alirezaafkar.sundatepicker.DatePicker
import com.alirezaafkar.sundatepicker.interfaces.DateSetListener
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import ir.reservs.reservs.R
import ir.reservs.reservs.data.DataManager
import ir.reservs.reservs.utils.RetrofitError
import ir.reservs.reservs.utils.TimeUtils
import java.util.*

class InformationPresenter(private val dataManager: DataManager,
                           private val compositeDisposable: CompositeDisposable)
    : InformationContract.Presenter, DateSetListener {


    private var view: InformationContract.View? = null
    private var birthday: String? = null

    override fun onAttach(view: InformationContract.View) {
        this.view = view
    }

    fun confirmInformation(name: String) {
        if (name.isEmpty()) {
            view!!.onError(R.string.input_your_name)
            return
        }

        val birthday = if (this.birthday.equals(null))
            TimeUtils.timestampToPersian(dataManager.getUserBirthday()?.toLong()!!)
        else
            this.birthday

        val jcal = TimeUtils.persianToTimestamp(birthday)
        Log.e("confirmInformation", "birthday: $jcal")
        val disposable = dataManager.updateInformation(name, jcal.toString())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    dataManager.setCurrentUserName(name)
                    dataManager.setCurrentUserAge(birthday)
                    view?.informationUpdated()
                }, { error -> RetrofitError.handle(view!!, error) })
        compositeDisposable.add(disposable)
    }

    fun getDate() {
        val date = Calendar.getInstance()
        DatePicker.Builder().id(1)
                .maxDate(date)
                .date(date)
                .build(this).show(view?.getFManager()!!, "")
    }


    fun initializeViews() {
        val birthdayTs = dataManager.getUserBirthday()?.toLong()
        var birthday = "-"
        if (birthdayTs != null) {
            birthday = TimeUtils.timestampToPersian(birthdayTs).toString()
        }
        view!!.initializeViews(dataManager.getCurrentUserName(), birthday)
    }


    override fun onDateSet(id: Int, calendar: Calendar?, day: Int, month: Int, year: Int) {
        this.birthday = "${year}/${month}/${day}"
        view!!.displayDate(TimeUtils.dateDisplayFormat(year, month, day))
    }

    override fun onDetach() {
        if (!compositeDisposable.isDisposed)
            compositeDisposable.clear()

        view = null
    }
}
