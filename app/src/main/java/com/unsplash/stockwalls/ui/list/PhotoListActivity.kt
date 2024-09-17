package com.unsplash.stockwalls.ui.list

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.unsplash.stockwalls.R
import com.unsplash.stockwalls.data.model.UnsplashPhotoItemDto
import com.unsplash.stockwalls.ui.components.image.LoadNetworkImage
import com.unsplash.stockwalls.ui.components.toolbar.ToolbarTop
import com.unsplash.stockwalls.ui.detail.FullPhotoActivity
import com.unsplash.stockwalls.ui.theme.StockWallsTheme
import com.unsplash.stockwalls.utils.openActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PhotoListActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val context = LocalView.current.context
            PhotoListScreen { unsplashPhotoItemDto ->
                context.openActivity(FullPhotoActivity::class.java) {
                    putParcelable(FullPhotoActivity.PHOTO_KEY, unsplashPhotoItemDto)
                }
            }
        }
    }
}

@Composable
fun PhotoListScreen(navigateToDetail: (photo: UnsplashPhotoItemDto) -> Unit) {
    Column {
        ToolbarTop(title = stringResource(id = R.string.app_name))
        PhotoGrid(navigateToDetail)
    }
}

@Composable
fun PhotoGrid(navigateToDetail: (photo: UnsplashPhotoItemDto) -> Unit) {
    val viewModel: PhotoListViewModel = hiltViewModel()
    val photoListUiState by viewModel.photoListUiState.collectAsStateWithLifecycle()

    val spacing = StockWallsTheme.spacing

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .fillMaxSize()
            .padding(spacing.sm)
    ) {
        items(photoListUiState.photosList) { photo ->
            PhotoCard(photo = photo, spacing = spacing, navigateToDetail = navigateToDetail)
        }
    }
}

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
