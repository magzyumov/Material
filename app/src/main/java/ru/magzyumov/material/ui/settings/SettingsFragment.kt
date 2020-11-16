package ru.magzyumov.material.ui.settings

import android.os.Bundle
import android.view.*
import ru.magzyumov.material.R
import ru.magzyumov.material.common.Constants
import ru.magzyumov.material.common.helpers.PreferenceHelper
import ru.magzyumov.material.databinding.FragmentSettingsBinding
import ru.magzyumov.material.ui.base.BaseFragment
import javax.inject.Inject


class SettingsFragment: BaseFragment(R.layout.fragment_settings) {
    override val binding by viewBinding(FragmentSettingsBinding::bind)

    @Inject
    lateinit var preferenceHelper: PreferenceHelper

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fragmentInteraction.changePageTitle("Theme selection")

        binding.buttonThemeOne.setOnClickListener {
            preferenceHelper.setIntPreference(Constants.Preferences.THEME, 1)
            fragmentInteraction.updateActivity()
        }
        binding.buttonThemeTwo.setOnClickListener {
            preferenceHelper.setIntPreference(Constants.Preferences.THEME, 2)
            fragmentInteraction.updateActivity()
        }
        binding.buttonThemeThree.setOnClickListener {
            preferenceHelper.setIntPreference(Constants.Preferences.THEME, 3)
            fragmentInteraction.updateActivity()
        }

    }

}