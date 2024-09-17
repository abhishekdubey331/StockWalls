package com.unsplash.stockwalls.ui.theme

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.unsplash.stockwalls.R

object StockWallsTheme {
    val spacing = Spacing()
    val fontFamily = FontFamily(
        Font(R.font.jost_book, FontWeight.Normal)
    )
    val semiTransparentBlack = Color(0x80000000)
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
