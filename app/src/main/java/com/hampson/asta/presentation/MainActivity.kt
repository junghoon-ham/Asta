package com.hampson.asta.presentation

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.MenuProvider
import androidx.core.view.isGone
import androidx.core.view.isVisible
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
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val connectViewModel by viewModels<ConnectViewModel>()

    lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    private val mainMenuId = R.menu.main_app_bar_menu
    private val defaultMenuId = R.menu.default_app_bar_menu
    private val myPageMenuId = R.menu.my_page_app_bar_menu

    private lateinit var menuProvider: MenuProvider

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen().apply {
            setKeepOnScreenCondition { connectViewModel.isLoading.value }
        }

        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setupJetpackNavigation()
        setupClickListener()
    }

    private fun setupClickListener() {
        binding.buttonRegisterAuction.setOnClickListener {
            navigateFragment(R.id.fragment_register_auction)
            binding.floatingActionMenu.collapse()
        }

        binding.buttonRegisterAppraisal.setOnClickListener {
            Toast.makeText(this, "test", Toast.LENGTH_SHORT).show()
            binding.floatingActionMenu.collapse()
        }
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

        setupActionBar()
        setupVisibleActionBar()
        setupVisibleFloatingButton()
    }

    private fun setupActionBar() {
        //supportActionBar?.setShowHideAnimationEnabled(false)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.fragment_detail_auction,
                R.id.fragment_detail_appraisal -> supportActionBar?.hide()
                R.id.fragment_home,
                R.id.fragment_appraisal        -> setupMenuProvider(mainMenuId)
                R.id.fragment_my_page          -> setupMenuProvider(myPageMenuId)
                else                           -> setupMenuProvider(defaultMenuId)
            }
        }
    }

    private fun setupVisibleActionBar() {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.fragment_detail_auction,
                R.id.fragment_detail_appraisal -> supportActionBar?.hide()
                else                           -> supportActionBar?.show()
            }
        }
    }

    private fun setupVisibleFloatingButton() {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.fragment_home,
                R.id.fragment_appraisal -> binding.floatingActionMenu.isVisible = true
                else                    -> binding.floatingActionMenu.isGone = true
            }
        }
    }

    private fun setupMenuProvider(menuId: Int) {
        if (this::menuProvider.isInitialized) removeMenuProvider(menuProvider)

        menuProvider = object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(menuId, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return true
            }
        }

        addMenuProvider(menuProvider)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_wish_list -> {}
            R.id.menu_category  -> navigateFragment(R.id.fragment_category)
            R.id.menu_search    -> {}
            else                -> onBackPressed()
        }

        return super.onOptionsItemSelected(item)
    }

    fun setBottomNavigationVisibility(visibility: Int) {
        binding.bottomNavigationView.visibility = visibility
    }

    private fun navigateFragment(fragment: Int) {
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