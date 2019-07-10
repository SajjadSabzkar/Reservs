package ir.reservs.reservs.di.component;

import dagger.Component
import ir.reservs.reservs.di.PerFragment
import ir.reservs.reservs.di.module.FragmentModule
import ir.reservs.reservs.ui.login.login.LoginFragment
import ir.reservs.reservs.ui.login.register.RegisterFragment
import ir.reservs.reservs.ui.main.history.HistoryFragment
import ir.reservs.reservs.ui.main.information.InformationFragment
import ir.reservs.reservs.ui.main.password.PasswordFragment
import ir.reservs.reservs.ui.main.reserve.ReserveFragment
import ir.reservs.reservs.ui.main.results.ResultFragment
import ir.reservs.reservs.ui.main.salons.SalonListFragment
import ir.reservs.reservs.ui.main.search.SearchFragment
import ir.reservs.reservs.ui.main.settings.SettingsFragment
import ir.reservs.reservs.ui.main.times.TimesFragment

@PerFragment
@Component(dependencies = [ApplicationComponent::class], modules = [FragmentModule::class])
interface FragmentComponent {
    fun inject(fragment: SettingsFragment)
    fun inject(fragment: HistoryFragment)
    fun inject(fragment: SearchFragment)
    fun inject(fragment: ResultFragment)
    fun inject(fragment: ReserveFragment)
    fun inject(fragment: SalonListFragment)
    fun inject(fragment: InformationFragment)
    fun inject(fragment: PasswordFragment)
    fun inject(fragment: TimesFragment)
    fun inject(fragment: LoginFragment)
    fun inject(fragment: RegisterFragment)
}