package com.unsplash.stockwalls.ui.list

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import com.unsplash.stockwalls.common.ONE_FLOAT
import com.unsplash.stockwalls.ui.components.image.LoadNetworkImage
import com.unsplash.stockwalls.ui.mapper.PhotoUIModel
import com.unsplash.stockwalls.ui.theme.StockWallsTheme

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.PhotoCard(
    animatedVisibilityScope: AnimatedVisibilityScope,
    photo: PhotoUIModel,
    spacing: StockWallsTheme.Spacing,
    navigateToDetail: (photo: PhotoUIModel) -> Unit
) {
    Card(
        modifier = Modifier
            .padding(spacing.xs)
            .aspectRatio(ONE_FLOAT),
        elevation = spacing.md,
        shape = RoundedCornerShape(spacing.sm),
        backgroundColor = Color.White
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            LoadNetworkImage(
                photoUIModel = photo,
                contentDescription = "Photo card",
                modifier = Modifier
                    .clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }
                    ) {
                        navigateToDetail(photo)
                    }
                    .sharedElement(
                        state = rememberSharedContentState(
                            key = "image-${photo.id}"
                        ),
                        animatedVisibilityScope = animatedVisibilityScope
                    )
                    .fillMaxSize()
                    .clip(RoundedCornerShape(spacing.sm))
            )
        }
    }
}
