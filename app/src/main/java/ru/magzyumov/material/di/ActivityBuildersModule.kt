package ru.magzyumov.material.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import ru.magzyumov.material.ui.main.MainActivity


// Declare all the activity here , dependency of activity are provided by this module

@Module
abstract class ActivityBuildersModule {

    @ContributesAndroidInjector(modules = [FragmentBuildersModule::class, ViewModelModule::class])
    abstract fun contributeMainActivity(): MainActivity

}