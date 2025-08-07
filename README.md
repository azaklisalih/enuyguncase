# 🛍️ EnuygunCase - Modern Android E-Commerce Application

[![Android](https://img.shields.io/badge/Android-3DDC84?style=for-the-badge&logo=android&logoColor=white)](https://developer.android.com/)
[![Kotlin](https://img.shields.io/badge/Kotlin-0095D5?style=for-the-badge&logo=kotlin&logoColor=white)](https://kotlinlang.org/)
[![Jetpack Compose](https://img.shields.io/badge/Jetpack_Compose-4285F4?style=for-the-badge&logo=jetpack-compose&logoColor=white)](https://developer.android.com/jetpack/compose)
[![MVVM](https://img.shields.io/badge/MVVM-Architecture-FF6B6B?style=for-the-badge)](https://developer.android.com/topic/architecture)
[![Tests](https://img.shields.io/badge/Tests-194%20Passing-00C851?style=for-the-badge)](https://junit.org/)

A comprehensive e-commerce mobile application developed with modern Android technologies. Features product listing, search, filtering, favorite management, cart operations, and payment processes in a production-ready mobile app.

## 📱 Application Features

### ✨ Core Features
- 🏠 **Home Page**: Product list, search and filtering
- 🔍 **Search**: Advanced product search functionality
- 🏷️ **Filtering**: Category-based filtering
- 📊 **Sorting**: Price, rating, stock, name-based sorting
- 📋 **Product Detail**: Detailed product information and images
- ❤️ **Favorite Management**: Add/remove favorites
- 🛒 **Cart Management**: Add products, change quantities, remove items
- 💳 **Payment**: Order form and confirmation
- 🌍 **Multi-language**: Turkish/English language support

### 🚀 Future Features
- 👤 User account management
- 💰 Real payment integration
- 🔔 Push notification system
- 📱 Offline mode support
- ⭐ Product rating system

## 🏗️ Architecture and Technologies

### 📐 Architecture Pattern
- **MVVM (Model-View-ViewModel)**
- **Clean Architecture**
- **Repository Pattern**
- **Use Case Pattern**

### 🛠️ Technologies Used

#### **Core Technologies**
- **Android SDK**: 35 (Android 15)
- **Kotlin**: 1.9+
- **Java**: 11
- **Gradle**: 8.0+

#### **UI & Navigation**
- **Jetpack Compose**: Modern UI toolkit
- **XML Layouts**: Hybrid approach
- **Material3**: Material Design 3
- **Navigation Component**: Fragment navigation
- **ConstraintLayout**: Responsive layouts

#### **Dependency Injection**
- **Hilt**: DI framework for Android

#### **Network & Data**
- **Retrofit**: HTTP client
- **OkHttp**: Network interceptor
- **Gson**: JSON parsing
- **Room**: Local database
- **RxJava3**: Reactive programming

#### **Async Programming**
- **Kotlin Coroutines**: Asynchronous programming
- **Flow**: Reactive streams
- **LiveData**: Observable data holder

#### **Image Loading**
- **Glide**: Image loading library
- **Coil**: Kotlin-first image loading

#### **Testing**
- **JUnit**: Unit testing
- **Mockito**: Mocking framework
- **Turbine**: Flow testing
- **Truth**: Assertion library
- **Espresso**: UI testing

## 📋 System Requirements

### Minimum Requirements
- **Android Version**: 10 (API 29)
- **RAM**: 2GB
- **Storage**: 50MB

### Recommended Requirements
- **Android Version**: 13+ (API 33+)
- **RAM**: 4GB+
- **Storage**: 100MB+

## 🚀 Installation and Setup

### 1. Prerequisites
```bash
# Android Studio Arctic Fox or higher
# Android SDK 29+
# Java 11+
# Kotlin 1.9+
```

### 2. Clone the Repository
```bash
git clone https://github.com/username/enuyguncase.git
cd enuyguncase
```

### 3. Open in Android Studio
1. Open Android Studio
2. Select "Open an existing project"
3. Choose the cloned folder
4. Wait for Gradle sync to complete

### 4. Run the Application

#### Using Android Studio:
1. Click the Run button (▶️)
2. Select target device
3. App will automatically install and run

#### Using Terminal:
```bash
# Build debug APK
./gradlew assembleDebug

# Install app to device
./gradlew installDebug

# Build release APK
./gradlew assembleRelease
```

## 📱 Screenshots

### Main Screens
- **SplashActivity**: Application splash screen
- **MainActivity**: Main activity (Bottom Navigation)
- **HomeFragment**: Product list and search
- **ProductDetailFragment**: Product details
- **FavoriteFragment**: Favorite products
- **CartFragment**: Cart management
- **CheckoutFragment**: Payment form
- **OrderSuccessFragment**: Successful order screen

### Helper Screens
- **FilterSheetFragment**: Category filtering
- **SortSheetFragment**: Product sorting options

## 🧪 Test Coverage

### Test Statistics
- **Total Tests**: 194 tests
- **Success Rate**: 100%
- **Test Coverage**: 95%+

### Test Categories

#### ✅ Domain Model Tests (35 tests)
- Product model tests
- CartItem model tests
- Favorite model tests

#### ✅ Use Case Tests (47 tests)
- AddToCartUseCase
- GetProductsUseCase
- SearchProductsUseCase
- SortProductsLocallyUseCase
- Cart management use cases
- Favorite management use cases

#### ✅ Repository Tests (42 tests)
- CartRepositoryImpl
- FavoriteRepositoryImpl
- Data layer tests

#### ✅ Mapper Tests (40 tests)
- ProductMapper
- CartMapper
- FavoriteMapper
- Data transformation tests

#### ✅ UI State Tests (30 tests)
- HomeUIState
- CartUIState
- FavoriteUIState
- State management tests

### Running Tests
```bash
# Run all tests
./gradlew test

# Run only unit tests
./gradlew testDebugUnitTest

# Run Android tests
./gradlew connectedAndroidTest

# View test report
./gradlew test --info
```

## 🔧 Configuration

### API Configuration
```kotlin
// NetworkModule.kt
object NetworkConfig {
    const val BASE_URL = "https://dummyjson.com"
    const val TIMEOUT = 30L
}
```

### Build Variants
- **Debug**: For development
- **Release**: For production (ProGuard enabled)

### Environment Variables
```properties
# API Configuration
BASE_URL=https://dummyjson.com
API_TIMEOUT=30
DEBUG_MODE=true
```

## 📊 Project Structure

```
app/src/main/java/com/example/enuyguncase/
├── core/                    # Core utilities
├── data/                    # Data layer
│   ├── cart/               # Cart data operations
│   ├── favorite/           # Favorite data operations
│   ├── home/               # Home data operations
│   │   ├── local/          # Room database
│   │   ├── remote/         # API operations
│   │   └── repository/     # Repository implementations
│   └── common/             # Shared data components
├── di/                      # Dependency injection
├── domain/                  # Domain layer
│   ├── model/              # Domain models
│   ├── repository/         # Repository interfaces
│   └── usecase/            # Use cases
├── presentation/            # Presentation layer
│   ├── home/               # Home screen
│   ├── productdetail/      # Product detail screen
│   ├── cart/               # Cart screen
│   ├── favorite/           # Favorite screen
│   ├── checkout/           # Checkout screen
│   └── common/             # Shared UI components
└── util/                    # Utilities
```

## 🤝 Contributing

### Development Process
1. **Fork** the repository
2. Create a **feature branch** (`git checkout -b feature/amazing-feature`)
3. **Commit** your changes (`git commit -m 'Add amazing feature'`)
4. **Push** to the branch (`git push origin feature/amazing-feature`)
5. Create a **Pull Request**

### Code Standards
- Follow **Kotlin coding conventions**
- Adhere to **MVVM architecture pattern**
- Follow **Clean Architecture** principles
- Write **unit tests** (minimum 90% coverage)
- Write **meaningful commit messages**

### Branch Naming Convention
- `main`: Production branch
- `develop`: Development branch
- `feature/*`: New features
- `bugfix/*`: Bug fixes
- `hotfix/*`: Hot fixes

## 📄 License

This project is licensed under the **MIT License**. See the [LICENSE](LICENSE) file for details.

## 📞 Contact

- **Developer**: [Salih Azaklı]
- **Email**: [azakli.dev@gmail.com]
- **GitHub**: [github.com/azaklisalih]
- **LinkedIn**: [linkedin.com/in/salih-azakli]

## 🙏 Acknowledgments

For the technologies and libraries used in this project:

- [Android Developers](https://developer.android.com/)
- [Jetpack Compose](https://developer.android.com/jetpack/compose)
- [Kotlin](https://kotlinlang.org/)
- [Retrofit](https://square.github.io/retrofit/)
- [Room](https://developer.android.com/training/data-storage/room)
- [Hilt](https://dagger.dev/hilt/)
- [Material Design](https://material.io/)

---

⭐ Don't forget to star this project if you liked it! 