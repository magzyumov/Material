package ru.magzyumov.material.ui.base

import android.content.Context
import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import ru.magzyumov.material.ui.main.MainInteraction
import ru.magzyumov.material.utils.FragmentViewBindingDelegate


abstract class BaseFragment(layoutRes: Int): Fragment(layoutRes) {

    protected lateinit var fragmentInteraction: MainInteraction
    abstract val binding: ViewBinding

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is MainInteraction) fragmentInteraction = context
    }


    protected fun <T: ViewBinding> Fragment.viewBinding(viewBindingFactory: (View) -> T) =
        FragmentViewBindingDelegate(this, viewBindingFactory)
}