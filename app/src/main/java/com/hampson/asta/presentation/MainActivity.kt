package com.hampson.asta.presentation

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.hampson.asta.R
import com.hampson.asta.databinding.ActivityMainBinding
import com.hampson.asta.presentation.connect.ConnectViewModel

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val connectViewModel by viewModels<ConnectViewModel>()

    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen().apply {
            setKeepOnScreenCondition { connectViewModel.isLoading.value }
        }

        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setupJetpackNavigation()
    }

    private fun setupJetpackNavigation() {
        val host = supportFragmentManager
            .findFragmentById(R.id.main_nav_host_fragment) as NavHostFragment? ?: return
        navController = host.navController
        binding.bottomNavigationView.setupWithNavController(navController)

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.fragment_home,
                R.id.fragment_appraisal,
                R.id.fragment_ranking,
                R.id.fragment_bid,
                R.id.fragment_my_page
            )
        )

        setupActionBarWithNavController(navController, appBarConfiguration)

        //navController.addOnDestinationChangedListener { _: NavController, destination: NavDestination, _: Bundle? ->
        //    binding.bottomNavigationView.isVisible =
        //        appBarConfiguration.topLevelDestinations.contains(destination.id)
        //}
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_app_bar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.menu_wish_list -> {}
            R.id.menu_category -> {navController.navigate(R.id.fragment_category)}
            R.id.menu_search -> {}
        }

        return super.onOptionsItemSelected(item)
    }

    fun setBottomNavigationVisibility(visibility: Int) {
        binding.bottomNavigationView.visibility = visibility
        binding.floatingActionMenu.visibility = visibility
    }
}