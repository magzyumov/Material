package ru.magzyumov.material.ui.main

import android.os.Bundle

import android.view.*
import ru.magzyumov.material.R
import ru.magzyumov.material.databinding.FragmentFirstBinding
import ru.magzyumov.material.ui.base.BaseFragment


class FirstFragment: BaseFragment(R.layout.fragment_first){
    override val binding by viewBinding(FragmentFirstBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonGoToSettings.setOnClickListener {
            fragmentInteraction.openSecondActivity()
        }
    }
}