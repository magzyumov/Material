package ru.magzyumov.material.ui.main

import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.navigation.NavigationView
import ru.magzyumov.material.R
import ru.magzyumov.material.databinding.ActivityMainBinding
import ru.magzyumov.material.ui.base.BaseActivity

class MainActivity: BaseActivity(), MainInteraction {
    override val binding by viewBinding(ActivityMainBinding::inflate)

    private lateinit var navController: NavController
    private lateinit var toolbar: MaterialToolbar
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        toolbar = binding.includeAppBar.toolbar
        setSupportActionBar(toolbar)

        initDrawer(toolbar)

        navController = findNavController(R.id.nav_host_fragment)
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    override fun changePageTitle(title: String) {
        toolbar.title = title
    }

    override fun updateActivity() {
        recreateActivity()
    }

    private fun initDrawer(toolbar: MaterialToolbar) {

        val drawer: DrawerLayout = binding.drawerLayout
        val navigationView: NavigationView = binding.navView
        appBarConfiguration = AppBarConfiguration(
                setOf(
                        R.id.nav_home, R.id.nav_theme
                ),
                drawer
        )

        val drawerToggle = ActionBarDrawerToggle(this@MainActivity, drawer, toolbar, R.string.drawer_open, R.string.drawer_close)

        drawerToggle.isDrawerIndicatorEnabled = true
        drawer.addDrawerListener(drawerToggle)
        drawerToggle.syncState()
        navigationView.setNavigationItemSelectedListener {
            when (it.itemId){
                R.id.nav_home -> navController.navigate(R.id.nav_home)
                R.id.nav_theme -> navController.navigate(R.id.nav_theme)
            }
            binding.drawerLayout.closeDrawer(GravityCompat.START)
            true
        }
    }
}