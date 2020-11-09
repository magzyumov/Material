package ru.magzyumov.material.di


import dagger.Module
import dagger.android.ContributesAndroidInjector
import ru.magzyumov.material.ui.main.FirstFragment


// Declare all the fragments here , dependency of fragments are provided by this module

@Module
abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeFirstFragment(): FirstFragment

}