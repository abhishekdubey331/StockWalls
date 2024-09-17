package com.unsplash.stockwalls.ui.list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.unsplash.stockwalls.R
import com.unsplash.stockwalls.data.model.UnsplashPhotoItemDto
import com.unsplash.stockwalls.ui.components.loading.LoadingIndicator
import com.unsplash.stockwalls.ui.components.toolbar.ToolbarTop
import com.unsplash.stockwalls.ui.detail.FullPhotoActivity
import com.unsplash.stockwalls.ui.theme.StockWallsTheme
import com.unsplash.stockwalls.utils.openActivity

@Composable
fun PhotoListScreen() {
    val context = LocalView.current.context
    Column {
        ToolbarTop(title = stringResource(id = R.string.app_name))
        PhotoGrid { unsplashPhotoItem ->
            context.openActivity(FullPhotoActivity::class.java) {
                putParcelable(FullPhotoActivity.PHOTO_KEY, unsplashPhotoItem)
            }
        }
    }
}

@Composable
fun PhotoGrid(navigateToDetail: (photo: UnsplashPhotoItemDto) -> Unit) {
    val viewModel: PhotoListViewModel = hiltViewModel()
    val photoPagingItems = viewModel.photoPagingDataFlow.collectAsLazyPagingItems()
    val spacing = StockWallsTheme.spacing
    val listState = rememberLazyGridState()

    if (photoPagingItems.loadState.refresh is LoadState.Loading) {
        LoadingIndicator(
            modifier = Modifier.fillMaxSize()
        )
    }

    LazyVerticalGrid(
        state = listState,
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .fillMaxSize()
            .padding(spacing.sm)
    ) {
        // Display grid items
        items(
            count = photoPagingItems.itemCount,
            key = photoPagingItems.itemKey { item -> item.id.orEmpty() }
        ) { index ->
            photoPagingItems[index]?.let { item ->
                PhotoCard(photo = item, spacing = spacing, navigateToDetail = navigateToDetail)
            }
        }

        if (photoPagingItems.loadState.append is LoadState.Loading) {
            item(span = { GridItemSpan(2) }) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(spacing.sm),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
        }
    }
}
