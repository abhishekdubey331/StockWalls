package com.unsplash.stockwalls.ui.components.image

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.unsplash.stockwalls.R
import com.unsplash.stockwalls.ui.mapper.PhotoUIModel

@Composable
fun LoadNetworkImage(
    photoUIModel: PhotoUIModel,
    contentDescription: String,
    modifier: Modifier = Modifier,
    placeHolderEnabled: Boolean = true
) {
    val context = LocalContext.current

    AsyncImage(
        model = ImageRequest.Builder(context).data(photoUIModel.regularImageUrl).apply {
            if (placeHolderEnabled) {
                placeholder(R.drawable.placeholder)
            }
            listener(onSuccess = { _, _ ->
                // no-op
            })
        }.build(),
        contentDescription = contentDescription,
        modifier = modifier,
        contentScale = ContentScale.Crop
    )
}
