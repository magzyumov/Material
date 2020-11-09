package ru.magzyumov.material.common.helpers

import ru.magzyumov.material.R
import ru.magzyumov.material.common.Constants.Preferences.Companion.THEME
import javax.inject.Inject


class ThemeHelper @Inject constructor(
    private val preferenceHelper: PreferenceHelper
) {

    fun getCurrentTheme(): Int{
        return when(preferenceHelper.getIntPreference(THEME)){
            1 -> R.style.TestStyleOne
            2 -> R.style.TestStyleTwo
            3 -> R.style.TestStyleThree
            else -> R.style.AppTheme
        }
    }
}