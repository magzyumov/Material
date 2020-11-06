package ru.magzyumov.material.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import ru.magzyumov.material.ui.main.MainActivity
import ru.magzyumov.material.ui.second.SecondActivity


// Declare all the activity here , dependency of activity are provided by this module

@Module
abstract class ActivityBuildersModule {

    // Method #1
    @ContributesAndroidInjector(modules = [FragmentBuildersModule::class])
    abstract fun contributeMainActivity(): MainActivity

    // Method #2
    @ContributesAndroidInjector(modules = [])
    abstract fun contributeSecondActivity(): SecondActivity


}