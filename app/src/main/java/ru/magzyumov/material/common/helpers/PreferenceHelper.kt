package ru.magzyumov.material.common.helpers

import android.content.SharedPreferences
import javax.inject.Inject


class PreferenceHelper @Inject constructor(
    private val sharedPreferences: SharedPreferences
) {

    fun getIntPreference(parameter: String): Int {
        return sharedPreferences.getInt(parameter, 0)
    }

    fun setIntPreference(parameter: String, value: Int) {
        sharedPreferences.edit().putInt(parameter, value).apply()
    }
}