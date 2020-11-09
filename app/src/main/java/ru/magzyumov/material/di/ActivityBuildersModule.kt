package ru.magzyumov.material.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import ru.magzyumov.material.ui.main.MainActivity
import ru.magzyumov.material.ui.settings.SettingsActivity


// Declare all the activity here , dependency of activity are provided by this module

@Module
abstract class ActivityBuildersModule {

    @ContributesAndroidInjector(modules = [FragmentBuildersModule::class])
    abstract fun contributeMainActivity(): MainActivity

    @ContributesAndroidInjector(modules = [])
    abstract fun contributeSecondActivity(): SettingsActivity


}