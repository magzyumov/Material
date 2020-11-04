package ru.magzyumov.material.ui.second

import android.os.Bundle
import ru.magzyumov.material.databinding.ActivitySecondBinding
import ru.magzyumov.material.ui.base.BaseActivity
import ru.magzyumov.material.common.Constants.Common.Companion.SETTINGS
import ru.magzyumov.material.common.Constants.Common.Companion.THEME

class SecondActivity : BaseActivity() {
    override val binding by viewBinding(ActivitySecondBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.buttonThemeOne.setOnClickListener {
            setIntPreference(SETTINGS, THEME, 1)
            recreateActivity()
        }
        binding.buttonThemeTwo.setOnClickListener {
            setIntPreference(SETTINGS, THEME, 2)
            recreateActivity()
        }
        binding.buttonThemeThree.setOnClickListener {
            setIntPreference(SETTINGS, THEME, 3)
            recreateActivity()
        }
        binding.buttonThemeFour.setOnClickListener {
            setIntPreference(SETTINGS, THEME, 4)
            recreateActivity()
        }
        binding.buttonThemeFive.setOnClickListener {
            setIntPreference(SETTINGS, THEME, 5)
            recreateActivity()
        }
    }
}