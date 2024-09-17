package com.unsplash.stockwalls.ui.list

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.unsplash.stockwalls.R
import com.unsplash.stockwalls.common.ONE_FLOAT
import com.unsplash.stockwalls.ui.components.image.LoadNetworkImage
import com.unsplash.stockwalls.ui.mapper.PhotoUIModel
import com.unsplash.stockwalls.ui.theme.StockWallsTheme

@Composable
fun PhotoCard(
    photo: PhotoUIModel,
    spacing: StockWallsTheme.Spacing,
    navigateToDetail: (photo: PhotoUIModel) -> Unit
) {
    Card(
        modifier = Modifier
            .padding(spacing.xs)
            .aspectRatio(ONE_FLOAT)
            .clickable {
                navigateToDetail(photo)
            },
        elevation = spacing.md,
        shape = RoundedCornerShape(spacing.sm),
        backgroundColor = Color.White
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            LoadNetworkImage(
                imageUrl = photo.smallImageUrl,
                contentDescription = "Photo Card",
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(spacing.sm))
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomStart)
                    .background(StockWallsTheme.semiTransparentBlack)
                    .padding(
                        horizontal = spacing.sm, vertical = spacing.xs
                    ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_photo_grapher),
                    contentDescription = "Photographer",
                    tint = Color.Unspecified,
                )
                Spacer(modifier = Modifier.width(spacing.sm))
                Text(
                    text = photo.photographerName,
                    fontFamily = StockWallsTheme.fontFamily,
                    color = Color.White
                )
            }
        }
    }
}
