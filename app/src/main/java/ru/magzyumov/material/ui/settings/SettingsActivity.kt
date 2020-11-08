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

    private val MIN_TEXT_LENGTH = 8
    private val EMPTY_STRING = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.textInputLayout.hint = getString(R.string.hint_edit_text)
        binding.editText.setOnEditorActionListener(clickListener)

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

    private fun shouldShowError(): Boolean {
        val textLength: Int = binding.editText.text?.length ?: 0
        return textLength in 1 until MIN_TEXT_LENGTH
    }

    private fun showError() {
        binding.textInputLayout.error = getString(R.string.hint_edit_error)
    }

    private fun hideError() {
        binding.textInputLayout.error = EMPTY_STRING
    }

    private val clickListener: TextView.OnEditorActionListener = (TextView.OnEditorActionListener { v, actionId, event ->
        Log.e("!!!","QWEWQ")
        if (actionId == EditorInfo.IME_ACTION_GO && shouldShowError()){
            showError()
        } else {
            hideError()
        }
        true
    })
}