package com.example.enuyguncase.ui.theme
import androidx.compose.material3.Typography
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.enuyguncase.R

val AppFontFamily = FontFamily(
    Font(R.font.inter_regular, weight = FontWeight.Normal),
    Font(R.font.inter_medium,  weight = FontWeight.Medium),
    Font(R.font.inter_bold,    weight = FontWeight.Bold)
)

val AppTypography = Typography(
    displayLarge = androidx.compose.ui.text.TextStyle(
        fontFamily = AppFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize   = 57.sp
    ),
    displayMedium = androidx.compose.ui.text.TextStyle(
        fontFamily = AppFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize   = 45.sp
    ),
    displaySmall = androidx.compose.ui.text.TextStyle(
        fontFamily = AppFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize   = 36.sp
    ),
    
    headlineLarge = androidx.compose.ui.text.TextStyle(
        fontFamily = AppFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize   = 32.sp
    ),
    headlineMedium = androidx.compose.ui.text.TextStyle(
        fontFamily = AppFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize   = 28.sp
    ),
    headlineSmall = androidx.compose.ui.text.TextStyle(
        fontFamily = AppFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize   = 24.sp
    ),
    
    titleLarge = androidx.compose.ui.text.TextStyle(
        fontFamily = AppFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize   = 22.sp
    ),
    titleMedium = androidx.compose.ui.text.TextStyle(
        fontFamily = AppFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize   = 16.sp
    ),
    titleSmall = androidx.compose.ui.text.TextStyle(
        fontFamily = AppFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize   = 14.sp
    ),
    
    bodyLarge = androidx.compose.ui.text.TextStyle(
        fontFamily = AppFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize   = 16.sp
    ),
    bodyMedium = androidx.compose.ui.text.TextStyle(
        fontFamily = AppFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize   = 14.sp
    ),
    bodySmall = androidx.compose.ui.text.TextStyle(
        fontFamily = AppFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize   = 12.sp
    ),
    
    labelLarge = androidx.compose.ui.text.TextStyle(
        fontFamily = AppFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize   = 14.sp
    ),
    labelMedium = androidx.compose.ui.text.TextStyle(
        fontFamily = AppFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize   = 12.sp
    ),
    labelSmall = androidx.compose.ui.text.TextStyle(
        fontFamily = AppFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize   = 11.sp
    )
)
