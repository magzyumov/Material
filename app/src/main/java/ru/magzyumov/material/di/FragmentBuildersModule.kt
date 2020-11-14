package ru.magzyumov.material.di


import dagger.Module
import dagger.android.ContributesAndroidInjector
import ru.magzyumov.material.ui.main.FirstFragment
import ru.magzyumov.material.ui.settings.SettingsFragment


// Declare all the fragments here , dependency of fragments are provided by this module

@Module
abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeFirstFragment(): FirstFragment

    @ContributesAndroidInjector
    abstract fun contributeSettingsFragment(): SettingsFragment

}