package ru.magzyumov.material.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.google.android.material.snackbar.Snackbar
import ru.magzyumov.material.R
import ru.magzyumov.material.common.Constants.Common.Companion.SETTINGS
import ru.magzyumov.material.common.Constants.Common.Companion.THEME

abstract class BaseActivity: AppCompatActivity() {
    abstract val binding: ViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setActivityTheme()
        setContentView(binding.root)
    }

    protected fun showMessage(message: String){
        Snackbar.make(this.findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG).show()
    }

    protected fun getIntPreference(preference: String, parameter: String): Int {
        val sharedPref = getSharedPreferences(preference, MODE_PRIVATE)
        return sharedPref.getInt(parameter, 0)
    }

    protected fun setIntPreference(preference: String, parameter: String, value: Int) {
        val sharedPref = getSharedPreferences(preference, MODE_PRIVATE)
        sharedPref.edit().putInt(parameter, value).apply()
    }

    private fun setActivityTheme(){
        when(getIntPreference(SETTINGS, THEME)){
            1 -> setTheme(R.style.TestStyleOne)
            2 -> setTheme(R.style.TestStyleTwo)
            3 -> setTheme(R.style.TestStyleThree)
            4 -> setTheme(R.style.TestStyleFour)
            5 -> setTheme(R.style.TestStyleFive)
        }
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

