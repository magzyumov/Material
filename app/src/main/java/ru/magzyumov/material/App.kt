package ru.magzyumov.material

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import ru.magzyumov.material.di.DaggerAppComponent

class App: DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().application(this).build()
    }
}