package ru.magzyumov.material.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import dagger.android.support.DaggerAppCompatActivity
import ru.magzyumov.material.common.helpers.PreferenceHelper
import ru.magzyumov.material.common.helpers.ThemeHelper
import javax.inject.Inject

abstract class BaseActivity: DaggerAppCompatActivity() {
    abstract val binding: ViewBinding

    @Inject
    protected lateinit var themeHelper: ThemeHelper

    @Inject
    protected lateinit var preferenceHelper: PreferenceHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(themeHelper.getCurrentTheme())
        setContentView(binding.root)
    }

    protected fun recreateActivity(){
        recreate()
    }

    inline fun <T : ViewBinding> AppCompatActivity.viewBinding(
        crossinline bindingInflater: (LayoutInflater) -> T
    ) =
        lazy(LazyThreadSafetyMode.NONE) {
            bindingInflater.invoke(layoutInflater)
        }
}

