package com.example.enuyguncase.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

private val LightColors = lightColorScheme(
    primary      = Primary,
    onPrimary    = OnPrimary,
    surface      = Surface,
    onSurface    = OnSurface,
    surfaceVariant = SurfaceVariant,
    error        = Error,
    onError      = OnError
)

private val DarkColors = darkColorScheme(
    primary      = Primary,
    onPrimary    = OnPrimary,
    surface      = Black,
    onSurface    = White,
    surfaceVariant = SurfaceVariant,
    error        = Error,
    onError      = OnError
)

@Composable
fun EnuygunCaseTheme(
    useDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (useDarkTheme) DarkColors else LightColors

    MaterialTheme(
        colorScheme = colors,
        typography  = AppTypography,
        shapes      = Shapes(
            small  = RoundedCornerShape(4.dp),
            medium = RoundedCornerShape(8.dp),
            large  = RoundedCornerShape(0.dp)
        ),
        content     = content
    )
}
