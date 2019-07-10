package ir.reservs.reservs.di.module

import com.alirezaafkar.sundatepicker.DatePicker
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import ir.reservs.reservs.data.DataManager
import ir.reservs.reservs.di.PerFragment
import ir.reservs.reservs.ui.login.register.RegisterPresenter
import ir.reservs.reservs.ui.main.history.HistoryAdapter
import ir.reservs.reservs.ui.main.history.HistoryPresenter
import ir.reservs.reservs.ui.main.information.InformationPresenter
import ir.reservs.reservs.ui.main.password.PasswordPresenter
import ir.reservs.reservs.ui.main.reserve.ReservePresenter
import ir.reservs.reservs.ui.main.results.ResultPresenter
import ir.reservs.reservs.ui.main.salons.SalonListAdapter
import ir.reservs.reservs.ui.main.salons.SalonListPresenter
import ir.reservs.reservs.ui.main.times.TimesAdapter
import ir.reservs.reservs.ui.main.times.TimesPresenter
import ir.reservs.reservs.ui.main.times.WeekDayAdapter
import java.util.*

@Module
class FragmentModule {

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
    fun provideReservePresenter(dataManager: DataManager,
                                compositeDisposable: CompositeDisposable): ReservePresenter {
        return ReservePresenter(dataManager, compositeDisposable)
    }

    @Provides
    @PerFragment
    fun provideResultPresenter(dataManager: DataManager,
                               compositeDisposable: CompositeDisposable): ResultPresenter {
        return ResultPresenter(dataManager, compositeDisposable)
    }
}
