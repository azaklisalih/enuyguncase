package com.example.enuyguncase.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
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
    
    // Navigation
    @Composable
    fun navHome() = getString(R.string.nav_home)
    
    @Composable
    fun navFavorites() = getString(R.string.nav_favorites)
    
    @Composable
    fun navCart() = getString(R.string.nav_cart)
    
    // Home Screen
    @Composable
    fun homeProducts() = getString(R.string.home_products)
    
    @Composable
    fun homeFilter() = getString(R.string.home_filter)
    
    @Composable
    fun homeSort() = getString(R.string.home_sort)
    
    @Composable
    fun homeSearchHint() = getString(R.string.home_search_hint)
    
    @Composable
    fun homeTotalProducts(count: Int) = getString(R.string.home_total_products, count)
    
    @Composable
    fun homeTotalProductsFormat(count: Int) = getString(R.string.home_total_products_format, count)
    
    @Composable
    fun homeNoProducts() = getString(R.string.home_no_products)
    
    @Composable
    fun homeErrorMessage() = getString(R.string.home_error_message)
    
    // Filter Screen
    @Composable
    fun filterTitle() = getString(R.string.filter_title)
    
    @Composable
    fun filterClear() = getString(R.string.filter_clear)
    
    @Composable
    fun filterApply() = getString(R.string.filter_apply)
    
    @Composable
    fun filterAllCategories() = getString(R.string.filter_all_categories)
    
    // Sort Screen
    @Composable
    fun sortTitle() = getString(R.string.sort_title)
    
    @Composable
    fun sortPriceLowToHigh() = getString(R.string.sort_price_low_to_high)
    
    @Composable
    fun sortPriceHighToLow() = getString(R.string.sort_price_high_to_low)
    
    @Composable
    fun sortNameAToZ() = getString(R.string.sort_name_a_to_z)
    
    @Composable
    fun sortNameZToA() = getString(R.string.sort_name_z_to_a)
    
    @Composable
    fun sortRating() = getString(R.string.sort_rating)
    
    // Product Detail
    @Composable
    fun productDetailTitle() = getString(R.string.product_detail_title)
    
    @Composable
    fun productDetailAddToCart() = getString(R.string.product_detail_add_to_cart)
    
    @Composable
    fun productDetailAddToFavorites() = getString(R.string.product_detail_add_to_favorites)
    
    @Composable
    fun productDetailRemoveFromFavorites() = getString(R.string.product_detail_remove_from_favorites)
    
    @Composable
    fun productDetailDiscount(percentage: Int) = getString(R.string.product_detail_discount, percentage)
    
    @Composable
    fun productDetailPriceFormat(price: Double) = getString(R.string.product_detail_price_format, price)
    
    @Composable
    fun productDetailOriginalPriceFormat(price: Double) = getString(R.string.product_detail_original_price_format, price)
    
    @Composable
    fun productDetailDescription() = getString(R.string.product_detail_description)
    
    @Composable
    fun productDetailReviews() = getString(R.string.product_detail_reviews)
    
    @Composable
    fun productDetailRating() = getString(R.string.product_detail_rating)
    
    // Cart Screen
    @Composable
    fun cartTitle() = getString(R.string.cart_title)
    
    @Composable
    fun cartPriceLabel() = getString(R.string.cart_price_label)
    
    @Composable
    fun cartDiscountLabel() = getString(R.string.cart_discount_label)
    
    @Composable
    fun cartTotalLabel() = getString(R.string.cart_total_label)
    
    @Composable
    fun cartEmpty() = getString(R.string.cart_empty)
    
    @Composable
    fun cartTotal(amount: Double) = getString(R.string.cart_total, amount)
    
    @Composable
    fun cartCheckout() = getString(R.string.cart_checkout)
    
    @Composable
    fun cartRemoveItem() = getString(R.string.cart_remove_item)
    
    @Composable
    fun cartQuantity(quantity: Int) = getString(R.string.cart_quantity, quantity)
    
    @Composable
    fun cartPriceFormat(price: Double) = getString(R.string.cart_price_format, price)
    
    @Composable
    fun cartDiscountPriceFormat(price: Double) = getString(R.string.cart_discount_price_format, price)
    
    // Favorite Screen
    @Composable
    fun favoriteTitle() = getString(R.string.favorite_title)
    
    @Composable
    fun favoriteEmpty() = getString(R.string.favorite_empty)
    
    @Composable
    fun favoritePriceFormat(price: Double) = getString(R.string.favorite_price_format, price)
    
    @Composable
    fun favoriteRemove() = getString(R.string.favorite_remove)
    
    // Checkout Screen
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
    fun checkoutTotalAmount(amount: Double) = getString(R.string.checkout_total_amount, amount)
    
    @Composable
    fun checkoutConfirmOrder() = getString(R.string.checkout_confirm_order)
    
    @Composable
    fun checkoutConfirmOrderButton() = getString(R.string.checkout_confirm_order_button)
    
    @Composable
    fun checkoutSuccess() = getString(R.string.checkout_success)
    
    @Composable
    fun checkoutError() = getString(R.string.checkout_error)
    
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
    
    // Common
    @Composable
    fun commonLoading() = getString(R.string.common_loading)
    
    @Composable
    fun commonError() = getString(R.string.common_error)
    
    @Composable
    fun commonSuccess() = getString(R.string.common_success)
    
    @Composable
    fun commonCancel() = getString(R.string.common_cancel)
    
    @Composable
    fun commonOk() = getString(R.string.common_ok)
    
    @Composable
    fun commonYes() = getString(R.string.common_yes)
    
    @Composable
    fun commonNo() = getString(R.string.common_no)
    
    @Composable
    fun commonRetry() = getString(R.string.common_retry)
    
    @Composable
    fun commonBack() = getString(R.string.common_back)
    
    @Composable
    fun commonNext() = getString(R.string.common_next)
    
    @Composable
    fun commonPrevious() = getString(R.string.common_previous)
    
    // Currency
    @Composable
    fun currencyFormat(amount: Double) = getString(R.string.currency_format, amount)
    
    @Composable
    fun currencySymbol() = getString(R.string.currency_symbol)
    
} 