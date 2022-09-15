package com.hampson.asta.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.navOptions
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

    @SuppressLint("RestrictedApi")
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

        supportActionBar?.setShowHideAnimationEnabled(false)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.fragment_detail_auction) supportActionBar?.hide()
            else supportActionBar?.show()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_app_bar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.menu_wish_list -> {}
            R.id.menu_category -> navigateFragment(R.id.fragment_category)
            R.id.menu_search -> {}
        }

        return super.onOptionsItemSelected(item)
    }

    fun setBottomNavigationVisibility(visibility: Int) {
        binding.bottomNavigationView.visibility = visibility
        binding.floatingActionMenu.visibility = visibility
    }

    fun navigateFragment(fragment: Int) {
        val options = navOptions {
            anim {
                enter = androidx.navigation.ui.R.anim.nav_default_enter_anim
                exit = androidx.navigation.ui.R.anim.nav_default_exit_anim
                popEnter = androidx.navigation.ui.R.anim.nav_default_pop_enter_anim
                popExit = androidx.navigation.ui.R.anim.nav_default_pop_exit_anim
            }
        }

        navController.navigate(fragment, null, options)
    }
}