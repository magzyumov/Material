package ru.magzyumov.material.ui.second

import android.os.Bundle
import ru.magzyumov.material.databinding.ActivitySecondBinding
import ru.magzyumov.material.ui.base.BaseActivity
import ru.magzyumov.material.common.Constants.Preferences.Companion.SETTINGS
import ru.magzyumov.material.common.Constants.Preferences.Companion.THEME

class SecondActivity : BaseActivity() {
    override val binding by viewBinding(ActivitySecondBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.buttonThemeOne.setOnClickListener {
            baseService.preferenceHelper.setIntPreference(THEME, 1)
            recreateActivity()
        }
        binding.buttonThemeTwo.setOnClickListener {
            baseService.preferenceHelper.setIntPreference(THEME, 2)
            recreateActivity()
        }
        binding.buttonThemeThree.setOnClickListener {
            baseService.preferenceHelper.setIntPreference(THEME, 3)
            recreateActivity()
        }
    }
}