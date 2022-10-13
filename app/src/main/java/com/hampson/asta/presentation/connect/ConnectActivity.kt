package com.hampson.asta.presentation.connect

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.hampson.asta.R
import com.hampson.asta.databinding.ActivityConnectBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ConnectActivity : AppCompatActivity() {

    private val binding: ActivityConnectBinding by lazy {
        ActivityConnectBinding.inflate(layoutInflater)
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
            .findFragmentById(R.id.connect_nav_host_fragment) as NavHostFragment? ?: return
        navController = host.navController

        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.fragment_app_start)
        )
        setupActionBarWithNavController(navController, appBarConfiguration)

        //supportActionBar?.setShowHideAnimationEnabled(false)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.fragment_app_start) supportActionBar?.hide()
            else supportActionBar?.show()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}