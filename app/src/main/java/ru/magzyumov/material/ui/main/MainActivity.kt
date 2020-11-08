package ru.magzyumov.material.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.google.android.material.appbar.MaterialToolbar
import ru.magzyumov.material.R
import ru.magzyumov.material.databinding.ActivityMainBinding
import ru.magzyumov.material.ui.base.BaseActivity
import ru.magzyumov.material.ui.settings.SettingsActivity

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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menuSettings -> {
                showSnackBar("Open Settings?") {
                    startActivity(Intent(this@MainActivity, SettingsActivity::class.java))
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }
}