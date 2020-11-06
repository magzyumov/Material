package ru.magzyumov.material.ui.main

import android.content.Intent
import android.os.Bundle
import android.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.google.android.material.appbar.MaterialToolbar
import ru.magzyumov.material.R
import ru.magzyumov.material.databinding.ActivityMainBinding
import ru.magzyumov.material.ui.base.BaseActivity
import ru.magzyumov.material.ui.second.SecondActivity

class MainActivity: BaseActivity(), MainInteraction {
    override val binding by viewBinding(ActivityMainBinding::inflate)

    private lateinit var navController: NavController
    private lateinit var toolbar: MaterialToolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        navController = findNavController(R.id.nav_host_fragment)
    }

    override fun changePageTitle(title: String) {
        toolbar.title = title
    }

    override fun openSecondActivity() {
        startActivity(Intent(this, SecondActivity::class.java))
    }
}