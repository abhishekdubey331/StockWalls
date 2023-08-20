package com.unsplash.stockwalls.ui.list

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.unsplash.stockwalls.R
import com.unsplash.stockwalls.ui.components.image.LoadNetworkImage
import com.unsplash.stockwalls.ui.components.toolbar.ToolbarTop
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PhotoListActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Column {
                ToolbarTop(title = getString(R.string.app_name))
                ImageGrid()
            }
        }
    }
}

@Composable
fun ImageGrid(
    viewModel: PhotoListViewModel = hiltViewModel(),
) {
    val uiState = viewModel.photoListUiState.value
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.fillMaxSize()
    ) {
        items(uiState.photosList.size) { index ->
            Column {
                Card(
                    modifier = Modifier
                        .aspectRatio(1f)
                        .padding(1.dp),
                    elevation = 4.dp,
                    shape = RoundedCornerShape(4.dp),
                    backgroundColor = Color.White,
                ) {
                    LoadNetworkImage(
                        imageUrl = uiState.photosList[index].urls.small,
                        contentDescription = "",
                        modifier = Modifier.aspectRatio(1f)
                    )
                }
            }
        }
    }
}
