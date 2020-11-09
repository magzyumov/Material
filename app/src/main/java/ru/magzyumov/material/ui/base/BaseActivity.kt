package ru.magzyumov.material.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import dagger.android.support.DaggerAppCompatActivity
import ru.magzyumov.material.di.services.BaseServices
import javax.inject.Inject

abstract class BaseActivity: DaggerAppCompatActivity() {
    abstract val binding: ViewBinding

    @Inject
    lateinit var baseService: BaseServices

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(baseService.themeHelper.getCurrentTheme())
        setContentView(binding.root)
    }

    protected fun showSnackBar(message: String, action: (View) -> Unit){
        baseService.snackBarHelper.show(this, message, action)
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

