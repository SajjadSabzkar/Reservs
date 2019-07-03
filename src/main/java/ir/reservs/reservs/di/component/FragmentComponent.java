package ir.reservs.reservs.di.component;

import dagger.Component;
import ir.reservs.reservs.di.PerFragment;
import ir.reservs.reservs.di.module.FragmentModule;
import ir.reservs.reservs.ui.login.login.LoginFragment;
import ir.reservs.reservs.ui.login.register.RegisterFragment;
import ir.reservs.reservs.ui.main.details.DetailsFragment;
import ir.reservs.reservs.ui.main.history.HistoryFragment;
import ir.reservs.reservs.ui.main.information.InformationFragment;
import ir.reservs.reservs.ui.main.password.PasswordFragment;
import ir.reservs.reservs.ui.main.results.ResultFragment;
import ir.reservs.reservs.ui.main.salons.SalonListFragment;
import ir.reservs.reservs.ui.main.search.SearchFragment;
import ir.reservs.reservs.ui.main.settings.SettingsFragment;
import ir.reservs.reservs.ui.main.times.TimesFragment;

@PerFragment
@Component(dependencies = ApplicationComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {
    void inject(HistoryFragment fragment);

    void inject(SettingsFragment fragment);

    void inject(SearchFragment fragment);

    void inject(ResultFragment fragment);

    void inject(DetailsFragment fragment);

    void inject(SalonListFragment fragment);

    void inject(InformationFragment fragment);

    void inject(PasswordFragment fragment);

    void inject(TimesFragment fragment);

    void inject(LoginFragment fragment);

    void inject(RegisterFragment fragment);
}