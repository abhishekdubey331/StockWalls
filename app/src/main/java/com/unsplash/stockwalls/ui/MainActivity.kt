package com.unsplash.stockwalls.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.unsplash.stockwalls.ui.detail.FullPhotoScreenFlow
import com.unsplash.stockwalls.ui.list.PhotoListScreenFlow
import com.unsplash.stockwalls.ui.theme.StockWallsTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.serialization.Serializable

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            StockWallsTheme {
                NavHost(
                    navController = navController,
                    startDestination = PhotoListScreen
                ) {
                    composable<PhotoListScreen> {
                        PhotoListScreenFlow {
                            navController.navigate(FullPhotoScreen(it.id))
                        }
                    }
                    composable<FullPhotoScreen> {
                        val args = it.toRoute<FullPhotoScreen>()
                        FullPhotoScreenFlow(photoId = args.photoId) {
                            navController.navigateUp()
                        }
                    }
                }
            }
        }
    }
}

@Serializable
object PhotoListScreen

@Serializable
data class FullPhotoScreen(
    val photoId: String
)
