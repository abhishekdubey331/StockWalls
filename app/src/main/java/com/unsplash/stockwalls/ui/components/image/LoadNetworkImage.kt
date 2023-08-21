package com.unsplash.stockwalls.ui.components.image

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.unsplash.stockwalls.R

@Composable
fun LoadNetworkImage(
    imageUrl: String?,
    contentDescription: String,
    modifier: Modifier,
    showAnimProgress: Boolean = true
) {
    val builder = ImageRequest.Builder(LocalContext.current).data(imageUrl).apply {
        if (showAnimProgress) {
            placeholder(R.drawable.placeholder)
        }
    }
    Image(
        painter = rememberAsyncImagePainter(
            builder.build()
        ),
        contentDescription = contentDescription,
        contentScale = ContentScale.Crop,
        modifier = modifier
            .background(color = MaterialTheme.colors.surface)
    )
}
