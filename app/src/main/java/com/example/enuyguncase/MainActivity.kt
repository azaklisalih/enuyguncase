package com.example.enuyguncase

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.findNavController
import com.example.enuyguncase.databinding.ActivityMainBinding
import com.example.enuyguncase.presentation.cart.CartViewModel
import com.example.enuyguncase.presentation.common.navigation.NavigationRouter
import com.example.enuyguncase.util.LocaleHelper
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val cartViewModel: CartViewModel by viewModels()
    
    @Inject
    lateinit var navigationRouter: NavigationRouter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        

        val savedLanguage = LocaleHelper.getLanguage(this)
        LocaleHelper.setLocale(this, savedLanguage)


        WindowCompat.setDecorFitsSystemWindows(window, false)
        

        val windowInsetsController = WindowCompat.getInsetsController(window, window.decorView)
        windowInsetsController.isAppearanceLightStatusBars = true
        

        window.setBackgroundDrawableResource(android.R.color.white)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val navController = findNavController(R.id.nav_host_fragment_content_main)

        binding.bottomNav.setOnItemSelectedListener { item ->
            navigationRouter.handleBottomNavigation(navController, item.itemId)
        }

        binding.bottomNav.setOnItemReselectedListener { item ->
            navController.popBackStack(item.itemId, false)
        }
        setupCartBadge()

    }

    private fun setupCartBadge() {
        val bottomNav = binding.bottomNav

        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                cartViewModel.uiState.collect { uiState ->
                    val count = uiState.cartItems.sumOf { it.quantity }
                    val badge = bottomNav.getOrCreateBadge(R.id.cartFragment)
                    badge.isVisible = count > 0
                    badge.number    = count
                }
            }
        }
    }

}