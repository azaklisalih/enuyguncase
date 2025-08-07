package com.example.enuyguncase

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupWithNavController
import com.example.enuyguncase.databinding.ActivityMainBinding
import com.example.enuyguncase.presentation.cart.CartViewModel
import com.example.enuyguncase.ui.theme.EnuygunCaseTheme
import com.example.enuyguncase.util.LocaleHelper
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private val cartViewModel: CartViewModel by viewModels()

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
        binding.bottomNav.setupWithNavController(navController)

        setupCartBadge()



    }

    private fun setupCartBadge() {
        val bottomNav = binding.bottomNav

        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                cartViewModel.cartItems.collect { list ->
                    val count = list.sumOf { it.quantity }
                    val badge = bottomNav.getOrCreateBadge(R.id.cartFragment)
                    badge.isVisible = count > 0
                    badge.number    = count
                }
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}