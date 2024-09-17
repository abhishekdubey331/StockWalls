package com.unsplash.stockwalls.ui.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.unsplash.stockwalls.data.model.UnsplashPhotoItemDto
import com.unsplash.stockwalls.ui.components.image.LoadNetworkImage
import com.unsplash.stockwalls.ui.theme.StockWallsTheme

@Composable
fun PhotoCard(
    photo: UnsplashPhotoItemDto,
    spacing: StockWallsTheme.Spacing,
    navigateToDetail: (photo: UnsplashPhotoItemDto) -> Unit
) {
    Card(
        modifier = Modifier
            .aspectRatio(1f)
            .clickable {
                navigateToDetail(photo)
            },
        elevation = spacing.xs,
        shape = RoundedCornerShape(spacing.xs),
        backgroundColor = Color.White
    ) {
        LoadNetworkImage(
            imageUrl = photo.urls.small,
            contentDescription = "photo_card",
            modifier = Modifier.aspectRatio(1f)
        )
    }
}
