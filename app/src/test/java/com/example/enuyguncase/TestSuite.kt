package com.example.enuyguncase

import com.example.enuyguncase.domain.model.CartItemTest
import com.example.enuyguncase.domain.model.FavoriteTest
import com.example.enuyguncase.domain.model.ProductTest
import com.example.enuyguncase.domain.usecase.AddToCartUseCaseTest
import com.example.enuyguncase.domain.usecase.home.GetCategoriesUseCaseTest
import com.example.enuyguncase.domain.usecase.home.GetProductsUseCaseTest
import com.example.enuyguncase.domain.usecase.home.SearchProductsUseCaseTest
import com.example.enuyguncase.domain.usecase.home.GetProductsByCategoryUseCaseTest
import com.example.enuyguncase.domain.usecase.cart.GetCartItemsUseCaseTest
import com.example.enuyguncase.domain.usecase.cart.IncreaseCartQuantityUseCaseTest
import com.example.enuyguncase.domain.usecase.cart.DecreaseCartQuantityUseCaseTest
import com.example.enuyguncase.domain.usecase.cart.RemoveCartItemUseCaseTest
import com.example.enuyguncase.domain.usecase.cart.CheckoutUseCaseTest
import com.example.enuyguncase.domain.usecase.favorite.GetAllFavoritesUseCaseTest
import com.example.enuyguncase.domain.usecase.productdetail.AddFavoriteUseCaseTest
import com.example.enuyguncase.domain.usecase.productdetail.CheckFavoriteUseCaseTest
import com.example.enuyguncase.domain.usecase.productdetail.GetProductByIdUseCaseTest
import com.example.enuyguncase.domain.usecase.productdetail.RemoveFavoriteUseCaseTest
import com.example.enuyguncase.data.home.repository.CartRepositoryImplTest
import com.example.enuyguncase.data.home.repository.FavoriteRepositoryImplTest
import com.example.enuyguncase.data.home.mapper.ProductMapperTest
import com.example.enuyguncase.data.cart.CartMapperTest
import com.example.enuyguncase.data.favorite.mapper.FavoriteMapperTest

import org.junit.runner.RunWith
import org.junit.runners.Suite

/**
 * Test Suite that runs all unit tests in the application
 * This allows running all tests together or specific test categories
 */
@RunWith(Suite::class)
@Suite.SuiteClasses(
    // Domain Model Tests
    ProductTest::class,
    CartItemTest::class,
    FavoriteTest::class,
    
    // Use Case Tests
    AddToCartUseCaseTest::class,
    GetCategoriesUseCaseTest::class,
    GetProductsUseCaseTest::class,
    SearchProductsUseCaseTest::class,
    GetProductsByCategoryUseCaseTest::class,
    GetCartItemsUseCaseTest::class,
    IncreaseCartQuantityUseCaseTest::class,
    DecreaseCartQuantityUseCaseTest::class,
    RemoveCartItemUseCaseTest::class,
    CheckoutUseCaseTest::class,
    GetAllFavoritesUseCaseTest::class,
    AddFavoriteUseCaseTest::class,
    CheckFavoriteUseCaseTest::class,
    GetProductByIdUseCaseTest::class,
    RemoveFavoriteUseCaseTest::class,
    
    // Repository Tests
    CartRepositoryImplTest::class,
    FavoriteRepositoryImplTest::class,
    
    // Mapper Tests
    ProductMapperTest::class,
    CartMapperTest::class,
    FavoriteMapperTest::class,
    

)
class TestSuite 