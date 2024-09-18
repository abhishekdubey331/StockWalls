package com.unsplash.stockwalls.ui.components.image

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.unsplash.stockwalls.R

@Composable
fun LoadNetworkImage(
    imageUrl: String?,
    contentDescription: String,
    modifier: Modifier = Modifier,
    placeHolderEnabled: Boolean = true,
    showAnimProgress: Boolean = true,
    onSuccess: (() -> Unit)? = null,
    onError: (() -> Unit)? = null
) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current).data(imageUrl).apply {
            if (showAnimProgress) {
                crossfade(true)
            }
            if (placeHolderEnabled) {
                placeholder(R.drawable.placeholder)
            }
            listener(
                onSuccess = { _, _ -> onSuccess?.invoke() },
                onError = { _, _ -> onError?.invoke() }
            )
        }.build(),
        contentDescription = contentDescription,
        modifier = modifier,
        contentScale = ContentScale.Crop
    )
}
