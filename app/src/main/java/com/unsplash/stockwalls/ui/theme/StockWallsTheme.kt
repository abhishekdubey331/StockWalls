package com.unsplash.stockwalls.ui.theme

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

object StockWallsTheme {
    val spacing = Spacing()
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
