package com.unsplash.stockwalls.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Typography
import androidx.compose.material.darkColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.unsplash.stockwalls.R

object StockWallsTheme {
    val spacing = Spacing()
    val fontFamily = FontFamily(
        Font(R.font.jost_book, FontWeight.Normal)
    )
    val semiTransparentBlack = Color(0x80000000)

    // Spacing values used throughout the app
    data class Spacing(
        val oneDp: Dp = 1.dp,
        val xs: Dp = 4.dp,
        val sm: Dp = 8.dp,
        val md: Dp = 16.dp,
        val lg: Dp = 24.dp,
        val xl: Dp = 32.dp,
        val xxl: Dp = 48.dp
    )
}

private val DarkColors = darkColors(
    primary = Color(0xFF1e1e1e),
    primaryVariant = Color(0xFF1e1e1e),
    onPrimary = Color.White,
    secondary = Color(0xFF1e1e1e),
    secondaryVariant = Color(0xFF121212),
    onSecondary = Color.White,
    background = Color(0xFF121212),
    surface = Color(0xFF121212),
    onBackground = Color.White,
    onSurface = Color.White
)

private val AppTypography = Typography(
    defaultFontFamily = StockWallsTheme.fontFamily,
    body1 = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    h1 = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp
    )
)

@Composable
fun StockWallsTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colors = DarkColors,
        typography = AppTypography,
        content = content
    )
}
