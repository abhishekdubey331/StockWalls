package com.unsplash.stockwalls.ui.detail.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.unsplash.stockwalls.ui.components.image.LoadNetworkImage
import com.unsplash.stockwalls.ui.components.loading.LoadingIndicator
import com.unsplash.stockwalls.ui.mapper.PhotoUIModel

@Composable
fun FullPhotoScreen(photoItem: PhotoUIModel, onBackClick: () -> Unit) {
    var isImageLoaded by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        if (!isImageLoaded) {
            LoadingIndicator(modifier = Modifier.fillMaxSize())
        }

        LoadNetworkImage(
            imageUrl = photoItem.fullImageUrl,
            contentDescription = "Full Photo",
            placeHolderEnabled = false,
            modifier = Modifier.fillMaxSize(),
            showAnimProgress = false,
            onSuccess = { isImageLoaded = true },
            onError = { isImageLoaded = false }
        )

        IconButton(
            onClick = onBackClick,
            modifier = Modifier.align(Alignment.TopStart)
        ) {
            Icon(Icons.Default.ArrowBack, tint = Color.White, contentDescription = "Back")
        }
    }
}
