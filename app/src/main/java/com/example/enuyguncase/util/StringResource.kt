package com.example.enuyguncase.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.platform.LocalConfiguration
import java.text.NumberFormat
import com.example.enuyguncase.R

/**
 * String resource utility class for Compose
 * Provides easy access to string resources with proper formatting
 */
object StringResource {
    
    @Composable
    fun getString(resId: Int): String {
        return stringResource(id = resId)
    }
    
    @Composable
    fun getString(resId: Int, vararg formatArgs: Any): String {
        return stringResource(id = resId, formatArgs = formatArgs)
    }
    
    // Product Detail (used in ProductDetailScreen)
    @Composable
    fun productDetailAddToCart() = getString(R.string.product_detail_add_to_cart)
    
    @Composable
    fun productDetailAddedToCart() = getString(R.string.product_detail_added_to_cart)
    
    @Composable
    fun productDetailAddToFavorites() = getString(R.string.product_detail_add_to_favorites)
    
    @Composable
    fun productDetailRemoveFromFavorites() = getString(R.string.product_detail_remove_from_favorites)
    
    @Composable
    fun productDetailDiscount(percentage: Int) = getString(R.string.product_detail_discount, percentage)
    
    @Composable
    fun productDetailPriceFormat(price: Double) = formatCurrency(price)
    
    @Composable
    fun productDetailOriginalPriceFormat(price: Double) = formatCurrency(price)
    
    // Cart Screen (used in CartScreen)
    @Composable
    fun cartTitle() = getString(R.string.cart_title)
    
    @Composable
    fun cartPriceLabel() = getString(R.string.cart_price_label)
    
    @Composable
    fun cartDiscountLabel() = getString(R.string.cart_discount_label)
    
    @Composable
    fun cartTotalLabel() = getString(R.string.cart_total_label)
    
    @Composable
    fun cartCheckout() = getString(R.string.cart_checkout)
    
    @Composable
    fun cartRemoveItem() = getString(R.string.cart_remove_item)
    
    @Composable
    fun cartPriceFormat(price: Double) = formatCurrency(price)
    
    @Composable
    fun cartDiscountPriceFormat(price: Double) = formatCurrency(price)
    
    // Favorite Screen (used in FavoriteScreen)
    @Composable
    fun favoriteTitle() = getString(R.string.favorite_title)
    
    @Composable
    fun favoritePriceFormat(price: Double) = formatCurrency(price)
    
    // Checkout Screen (used in CheckoutScreen)
    @Composable
    fun checkoutTitle() = getString(R.string.checkout_title)
    
    @Composable
    fun checkoutName() = getString(R.string.checkout_name)
    
    @Composable
    fun checkoutEmail() = getString(R.string.checkout_email)
    
    @Composable
    fun checkoutPhone() = getString(R.string.checkout_phone)
    
    @Composable
    fun checkoutPhoneRequired() = getString(R.string.checkout_phone_required)
    
    @Composable
    fun checkoutNameRequired() = getString(R.string.checkout_name_required)
    
    @Composable
    fun checkoutEmailRequired() = getString(R.string.checkout_email_required)
    
    @Composable
    fun checkoutEmailInvalid() = getString(R.string.checkout_email_invalid)
    
    @Composable
    fun checkoutPhoneInvalid() = getString(R.string.checkout_phone_invalid)
    
    @Composable
    fun checkoutConfirmOrderButton() = getString(R.string.checkout_confirm_order_button)
    
    // Order Success Screen
    @Composable
    fun orderSuccessTitle() = getString(R.string.order_success_title)
    
    @Composable
    fun orderSuccessMessage(orderNumber: String) = getString(R.string.order_success_message, orderNumber)
    
    @Composable
    fun orderSuccessThankYou() = getString(R.string.order_success_thank_you)
    
    @Composable
    fun orderSuccessContinueShopping() = getString(R.string.order_success_continue_shopping)
    
    @Composable
    fun orderSuccessGoHome() = getString(R.string.order_success_go_home)
    
    @Composable
    fun orderSuccessOrderDetails() = getString(R.string.order_success_order_details)
    
    @Composable
    fun orderSuccessEstimatedDelivery() = getString(R.string.order_success_estimated_delivery)
    
    @Composable
    fun orderSuccessTrackingInfo() = getString(R.string.order_success_tracking_info)
    
    // Common (used in various screens)
    @Composable
    fun commonBack() = getString(R.string.common_back)
    
    // Currency formatting
    @Composable
    private fun formatCurrency(amount: Double): String {
        val locale = LocalConfiguration.current.locales[0]
        val numberFormat = NumberFormat.getCurrencyInstance(locale)
        return numberFormat.format(amount)
    }
    
} 