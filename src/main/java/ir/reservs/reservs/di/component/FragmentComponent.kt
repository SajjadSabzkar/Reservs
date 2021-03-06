package ir.reservs.reservs.di.component;

import dagger.Component
import ir.reservs.reservs.di.PerFragment
import ir.reservs.reservs.di.module.FragmentModule
import ir.reservs.reservs.ui.dialog.city.SelectCityFragment
import ir.reservs.reservs.ui.dialog.salon.SelectSalonFragment
import ir.reservs.reservs.ui.main.send.SendFragment
import ir.reservs.reservs.ui.main.verify.VerifyFragment
import ir.reservs.reservs.ui.main.history.HistoryFragment
import ir.reservs.reservs.ui.main.information.InformationFragment
import ir.reservs.reservs.ui.main.reserve.ReserveFragment
import ir.reservs.reservs.ui.main.results.ResultFragment
import ir.reservs.reservs.ui.main.salons.SalonListFragment
import ir.reservs.reservs.ui.main.search.SearchFragment
import ir.reservs.reservs.ui.main.settings.SettingsFragment
import ir.reservs.reservs.ui.main.splash.SplashFragment
import ir.reservs.reservs.ui.main.times.TimesFragment

@PerFragment
@Component(dependencies = [ApplicationComponent::class], modules = [FragmentModule::class])
interface FragmentComponent {
    fun inject(fragment: SplashFragment)
    fun inject(fragment: SettingsFragment)
    fun inject(fragment: HistoryFragment)
    fun inject(fragment: SearchFragment)
    fun inject(fragment: ResultFragment)
    fun inject(fragment: ReserveFragment)
    fun inject(fragment: SalonListFragment)
    fun inject(fragment: InformationFragment)
    fun inject(fragment: TimesFragment)
    fun inject(fragment: SendFragment)
    fun inject(fragment: VerifyFragment)
    fun inject(fragment: SelectCityFragment)
    fun inject(fragment: SelectSalonFragment)
}