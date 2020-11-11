package ru.magzyumov.material.ui.settings

import android.os.Bundle
import android.util.Log
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import ru.magzyumov.material.R
import ru.magzyumov.material.common.Constants.Preferences.Companion.THEME
import ru.magzyumov.material.databinding.ActivitySettingsBinding
import ru.magzyumov.material.ui.base.BaseActivity


class SettingsActivity : BaseActivity() {
    override val binding by viewBinding(ActivitySettingsBinding::inflate)

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