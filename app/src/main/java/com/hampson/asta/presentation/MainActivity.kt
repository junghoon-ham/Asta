package com.hampson.asta.presentation

import android.content.Context
import android.graphics.Rect
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
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
import com.app.imagepickerlibrary.ImagePicker
import com.app.imagepickerlibrary.ImagePicker.Companion.registerImagePicker
import com.app.imagepickerlibrary.listener.ImagePickerResultListener
import com.hampson.asta.R
import com.hampson.asta.databinding.ActivityMainBinding
import com.hampson.asta.presentation.connect.ConnectViewModel
import com.hampson.asta.presentation.register_auction.RegisterAuctionViewModel
import com.hampson.asta.util.PickerOptions
import com.hampson.asta.util.showTwoButtonDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), ImagePickerResultListener {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val connectViewModel by viewModels<ConnectViewModel>()
    private val registerAuctionViewModel by viewModels<RegisterAuctionViewModel>()

    lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    private val mainMenuId = R.menu.main_app_bar_menu
    private val defaultMenuId = R.menu.default_app_bar_menu
    private val myPageMenuId = R.menu.my_page_app_bar_menu

    private lateinit var menuProvider: MenuProvider

    private var imagePicker: ImagePicker? = registerImagePicker(this)
    private var _imagePickerListener: ImagePickerListener? = null
    fun setImagePickerListener(listener: ImagePickerListener) {
        _imagePickerListener = listener
    }

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
            if (registerAuctionViewModel.isWritingRegister.value) {
                showTwoButtonDialog(
                    context = this,
                    title = "안내",
                    message = "작성중인 경매등록이 있습니다.\n이어서 작성 하시겠습니까?",
                    onNegativeButton = {
                        registerAuctionViewModel.resetRegisterData()
                        moveRegister()
                    },
                    onPositiveButton = { moveRegister() }
                )
            } else {
                moveRegister()
            }
        }

        binding.buttonRegisterAppraisal.setOnClickListener {
            Toast.makeText(this, "test", Toast.LENGTH_SHORT).show()
            binding.floatingActionMenu.collapse()
        }
    }

    private fun moveRegister() {
        navigateFragment(R.id.fragment_register_auction)
        binding.floatingActionMenu.collapse()
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
                R.id.fragment_home -> setupMenuProvider(mainMenuId)
                R.id.fragment_my_page -> setupMenuProvider(myPageMenuId)
                else -> setupMenuProvider(defaultMenuId)
            }
        }
    }

    private fun setupVisibleActionBar() {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.fragment_detail_auction,
                R.id.fragment_detail_appraisal -> supportActionBar?.hide()
                else -> supportActionBar?.show()
            }
        }
    }

    private fun setupVisibleFloatingButton() {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.fragment_home,
                R.id.fragment_appraisal -> binding.floatingActionMenu.isVisible = true
                else -> binding.floatingActionMenu.isGone = true
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
            R.id.menu_category -> navigateFragment(R.id.fragment_category)
            R.id.menu_search -> navigateFragment(R.id.fragment_search)
            else -> onBackPressed()
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

    fun openImagePicker() {
        val pickerOptions = PickerOptions.default()

        if (imagePicker == null) return

        imagePicker!!
            .title(getString(R.string.select_auction_product))
            .multipleSelection(pickerOptions.allowMultipleSelection, pickerOptions.maxPickCount)
            .showCountInToolBar(pickerOptions.showCountInToolBar)
            .showFolder(pickerOptions.showFolders)
            .cameraIcon(pickerOptions.showCameraIconInGallery)
            .doneIcon(pickerOptions.isDoneIcon)
            .allowCropping(pickerOptions.openCropOptions)
            .compressImage(pickerOptions.compressImage)
            .maxImageSize(pickerOptions.maxPickSizeMB)
            .extension(pickerOptions.pickExtension)

        imagePicker?.open(pickerOptions.pickerType)
    }

    override fun onImagePick(uri: Uri?) {
        _imagePickerListener?.onImagePick(uri)
    }

    override fun onMultiImagePick(uris: List<Uri>?) {
        _imagePickerListener?.onMultiImagePick(uris)
    }

    override fun dispatchTouchEvent(event: MotionEvent?): Boolean {
        if (event?.action == MotionEvent.ACTION_DOWN) {
            if (currentFocus is EditText) {
                val currentFocus = currentFocus as EditText
                currentFocus.getGlobalVisibleRect(Rect())

                if (!Rect().contains(event.rawX.toInt(), event.rawY.toInt())) {
                    currentFocus.clearFocus()
                    val imm: InputMethodManager =
                        getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(currentFocus.windowToken, 0)
                }
            }
        }

        return super.dispatchTouchEvent(event)
    }
}