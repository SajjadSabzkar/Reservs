package ir.reservs.reservs.di.module

import android.app.AlertDialog
import android.content.Context
import com.alirezaafkar.sundatepicker.DatePicker
import dagger.Module
import dagger.Provides
import dmax.dialog.SpotsDialog
import io.reactivex.disposables.CompositeDisposable
import ir.reservs.reservs.R
import ir.reservs.reservs.data.DataManager
import ir.reservs.reservs.di.PerFragment
import ir.reservs.reservs.ui.dialog.city.SelectCityAdapter
import ir.reservs.reservs.ui.dialog.city.SelectCityPresenter

import ir.reservs.reservs.ui.dialog.salon.SelectSalonPresenter
import ir.reservs.reservs.ui.login.login.LoginPresenter
import ir.reservs.reservs.ui.login.register.RegisterPresenter
import ir.reservs.reservs.ui.main.history.HistoryAdapter
import ir.reservs.reservs.ui.main.history.HistoryPresenter
import ir.reservs.reservs.ui.main.information.InformationPresenter
import ir.reservs.reservs.ui.main.password.PasswordPresenter
import ir.reservs.reservs.ui.main.reserve.ReservePresenter
import ir.reservs.reservs.ui.main.results.ResultPresenter
import ir.reservs.reservs.ui.main.salons.SalonListAdapter
import ir.reservs.reservs.ui.main.salons.SalonListPresenter
import ir.reservs.reservs.ui.main.search.SearchPresenter
import ir.reservs.reservs.ui.main.settings.SettingsPresenter
import ir.reservs.reservs.ui.main.times.TimesAdapter
import ir.reservs.reservs.ui.main.times.TimesPresenter
import ir.reservs.reservs.ui.main.times.WeekDayAdapter

@Module
class FragmentModule(val context: Context) {

    @Provides
    @PerFragment
    fun provideCompositeDisposable(): CompositeDisposable {
        return CompositeDisposable()
    }

    @Provides
    @PerFragment
    fun provideHistoryPresenter(dataManager: DataManager, disposable: CompositeDisposable): HistoryPresenter {
        return HistoryPresenter(dataManager, disposable)
    }


    @Provides
    @PerFragment
    fun provideHistoryAdapter(): HistoryAdapter {
        return HistoryAdapter(ArrayList())
    }

    @Provides
    @PerFragment
    fun provideDatePicker(): DatePicker {
        return DatePicker()
    }

    @Provides
    @PerFragment
    fun provideDatePickerBuilder(): DatePicker.Builder {
        return DatePicker.Builder()
    }


    /*@Provides
    @PerFragment
    AlertDialog.Builder provideAlertDialogBuilder(AppCompatActivity activity) {
        return  AlertDialog.Builder(activity)
    }*/

    @Provides
    @PerFragment
    fun provideSalonAdapter(): SalonListAdapter {
        return SalonListAdapter(ArrayList())
    }

    @Provides
    @PerFragment
    fun provideSalonPresenter(dataManager: DataManager, compositeDisposable: CompositeDisposable): SalonListPresenter {
        return SalonListPresenter(dataManager, compositeDisposable)
    }

    @Provides
    @PerFragment
    fun provideInformationPresenter(dataManager: DataManager,
                                    compositeDisposable: CompositeDisposable): InformationPresenter {
        return InformationPresenter(dataManager, compositeDisposable)
    }

    @Provides
    @PerFragment
    fun providePasswordPresenter(dataManager: DataManager,
                                 compositeDisposable: CompositeDisposable): PasswordPresenter {
        return PasswordPresenter(dataManager, compositeDisposable)

    }

    @Provides
    @PerFragment
    fun provideTimesPresenter(dataManager: DataManager,
                              compositeDisposable: CompositeDisposable): TimesPresenter {
        return TimesPresenter(dataManager, compositeDisposable)
    }

    @Provides
    @PerFragment
    fun provideWeekDayAdapter(): WeekDayAdapter {
        return WeekDayAdapter(ArrayList())
    }

    @Provides
    @PerFragment
    fun provideTimesAdapter(): TimesAdapter {
        return TimesAdapter()
    }

    @Provides
    @PerFragment
    fun provideRegisterPresenter(dataManager: DataManager,
                                 compositeDisposable: CompositeDisposable): RegisterPresenter {
        return RegisterPresenter(dataManager, compositeDisposable)
    }

    @Provides
    @PerFragment
    fun provideLoginPresenter(dataManager: DataManager,
                              compositeDisposable: CompositeDisposable): LoginPresenter {
        return LoginPresenter(dataManager, compositeDisposable)
    }

    @Provides
    @PerFragment
    fun provideReservePresenter(dataManager: DataManager,
                                compositeDisposable: CompositeDisposable): ReservePresenter {
        return ReservePresenter(dataManager, compositeDisposable)
    }

    @Provides
    @PerFragment
    fun provideResultPresenter(): ResultPresenter {
        return ResultPresenter()
    }

    @Provides
    @PerFragment
    fun provideSelectCityPresenter(dataManager: DataManager,
                                   compositeDisposable: CompositeDisposable): SelectCityPresenter {
        return SelectCityPresenter(dataManager, compositeDisposable)
    }

    @Provides
    @PerFragment
    fun getSelectCityAdapter(): SelectCityAdapter {
        return SelectCityAdapter(ArrayList())
    }

    @Provides
    @PerFragment
    fun getSelectSalonPresenter(dataManager: DataManager,
                                compositeDisposable: CompositeDisposable): SelectSalonPresenter {
        return SelectSalonPresenter(dataManager, compositeDisposable)
    }

    @Provides
    @PerFragment
    fun getSearchPresenter(): SearchPresenter {
        return SearchPresenter()
    }

    @Provides
    @PerFragment
    fun getSettingsPresenter(dataManager: DataManager, compositeDisposable: CompositeDisposable): SettingsPresenter {
        return SettingsPresenter(dataManager, compositeDisposable)
    }

    @Provides
    @PerFragment
    fun provideWaitingDialog(): AlertDialog {
        return SpotsDialog.Builder()
                .setContext(context)
                .setCancelable(false)
                .setMessage(R.string.waiting)
                .build()
    }
}
