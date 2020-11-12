package ru.magzyumov.material.di

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import ru.magzyumov.material.utils.ViewModelProviderFactory


@Module
abstract class ViewModelFactoryModule {

    @Binds
    abstract fun bindViewModelFactory(viewModelProvideFactory: ViewModelProviderFactory): ViewModelProvider.Factory
}