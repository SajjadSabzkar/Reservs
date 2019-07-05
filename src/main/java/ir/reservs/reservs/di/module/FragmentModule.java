package ir.reservs.reservs.di.module;

import com.alirezaafkar.sundatepicker.DatePicker;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;
import ir.reservs.reservs.data.DataManager;
import ir.reservs.reservs.di.PerFragment;
import ir.reservs.reservs.ui.login.register.RegisterPresenter;
import ir.reservs.reservs.ui.main.history.HistoryAdapter;
import ir.reservs.reservs.ui.main.history.HistoryContract;
import ir.reservs.reservs.ui.main.history.HistoryPresenter;
import ir.reservs.reservs.ui.main.information.InformationPresenter;
import ir.reservs.reservs.ui.main.password.PasswordPresenter;
import ir.reservs.reservs.ui.main.reserve.ReservePresenter;
import ir.reservs.reservs.ui.main.salons.SalonListAdapter;
import ir.reservs.reservs.ui.main.salons.SalonListPresenter;
import ir.reservs.reservs.ui.main.times.TimesAdapter;
import ir.reservs.reservs.ui.main.times.TimesPresenter;
import ir.reservs.reservs.ui.main.times.WeekDayAdapter;

@Module
public class FragmentModule {

    @Provides
    @PerFragment
    CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }

    @Provides
    @PerFragment
    HistoryContract.Presenter provideHistoryPresenter(DataManager datamanager, CompositeDisposable disposable) {
        return new HistoryPresenter(datamanager, disposable);
    }


    @Provides
    @PerFragment
    HistoryAdapter provideHistoryAdapter() {
        return new HistoryAdapter(new ArrayList<>());
    }

    @Provides
    @PerFragment
    DatePicker provideDatePicker() {
        return new DatePicker();
    }

    @Provides
    @PerFragment
    DatePicker.Builder provideDatePickerBuilder() {
        return new DatePicker.Builder();
    }


    /*@Provides
    @PerFragment
    AlertDialog.Builder provideAlertDialogBuilder(AppCompatActivity activity) {
        return new AlertDialog.Builder(activity);
    }*/

    @Provides
    @PerFragment
    SalonListAdapter provideSalonAdapter() {
        return new SalonListAdapter(new ArrayList<>());
    }

    @Provides
    @PerFragment
    SalonListPresenter provideSalonPresenter(DataManager dataManager, CompositeDisposable compositeDisposable) {
        return new SalonListPresenter(dataManager, compositeDisposable);
    }

    @Provides
    @PerFragment
    InformationPresenter provideInformationPresenter(DataManager dataManager,
                                                     CompositeDisposable compositeDisposable) {
        return new InformationPresenter(dataManager, compositeDisposable);
    }

    @Provides
    @PerFragment
    PasswordPresenter providePasswordPresenter(DataManager dataManager,
                                               CompositeDisposable compositeDisposable) {
        return new PasswordPresenter(dataManager, compositeDisposable);

    }

    @Provides
    @PerFragment
    TimesPresenter provideTimesPresenter(DataManager dataManager, CompositeDisposable compositeDisposable) {
        return new TimesPresenter(dataManager, compositeDisposable);
    }

    @Provides
    @PerFragment
    WeekDayAdapter provideWeekDayAdapter() {
        return new WeekDayAdapter(new ArrayList<>());
    }

    @Provides
    @PerFragment
    TimesAdapter provideTimesAdapter() {
        return new TimesAdapter();
    }

    @Provides
    @PerFragment
    RegisterPresenter provideRegisterPresenter(DataManager dataManager, CompositeDisposable compositeDisposable) {
        return new RegisterPresenter(dataManager, compositeDisposable);
    }

    @Provides
    @PerFragment
    ReservePresenter provideReservePresenter(DataManager dataManager, CompositeDisposable compositeDisposable) {
        return new ReservePresenter(dataManager, compositeDisposable);
    }
}
