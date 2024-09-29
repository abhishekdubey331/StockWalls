package com.unsplash.stockwalls.ui.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.unsplash.stockwalls.ui.components.image.LoadNetworkImage
import com.unsplash.stockwalls.ui.components.loading.LoadingIndicator
import com.unsplash.stockwalls.ui.mapper.PhotoUIModel

@Composable
fun FullPhotoScreenFlow(
    photoId: String,
    navigateUp: () -> Unit
) {
    val viewModel: PhotoDetailViewModel = hiltViewModel()
    val state by viewModel.photoDetailsState.collectAsStateWithLifecycle()

    LaunchedEffect(photoId) {
        viewModel.getPhotoDetails(photoId)
    }

    when (val currentState = state) {
        is PhotoDetailViewState.Error -> ErrorIndicator(message = currentState.message)

        is PhotoDetailViewState.Loading -> LoadingIndicator(modifier = Modifier.fillMaxSize())

        is PhotoDetailViewState.Success -> {
            val photo = currentState.photo
            Photo(photoUIModel = photo, navigateUp = navigateUp)
        }
    }
}

@Composable
private fun Photo(
    photoUIModel: PhotoUIModel,
    navigateUp: () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        LoadNetworkImage(
            imageUrl = photoUIModel.fullImageUrl,
            contentDescription = "Full Photo",
            placeHolderEnabled = false,
            modifier = Modifier.fillMaxSize(),
            showAnimProgress = false
        )

        IconButton(
            onClick = navigateUp,
            modifier = Modifier.align(Alignment.TopStart)
        ) {
            Icon(
                Icons.AutoMirrored.Filled.ArrowBack,
                tint = Color.White,
                contentDescription = "Back button"
            )
        }
    }
}

@Composable
fun ErrorIndicator(message: String) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = message, color = Color.Red)
    }
}