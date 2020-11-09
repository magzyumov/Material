package ru.magzyumov.material.ui.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import dagger.android.support.DaggerFragment
import ru.magzyumov.material.ui.main.MainInteraction
import ru.magzyumov.material.utils.FragmentViewBindingDelegate


abstract class BaseFragment(@LayoutRes val contentLayoutId: Int): DaggerFragment() {

    protected lateinit var fragmentInteraction: MainInteraction
    abstract val binding: ViewBinding

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is MainInteraction) fragmentInteraction = context
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return if (contentLayoutId != 0) {
            inflater.inflate(contentLayoutId, container, false)
        } else null
    }


    protected fun <T: ViewBinding> Fragment.viewBinding(viewBindingFactory: (View) -> T) =
        FragmentViewBindingDelegate(this, viewBindingFactory)
}