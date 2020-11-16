package ru.magzyumov.material.di

import androidx.lifecycle.ViewModel

import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.magzyumov.material.ui.main.FirstViewModel


@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(FirstViewModel::class)
    abstract fun bindMainViewModel(moviesViewModel: FirstViewModel): ViewModel
}
