package com.unsplash.stockwalls.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
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

    @OptIn(ExperimentalSharedTransitionApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            StockWallsTheme {
                SharedTransitionLayout(
                    modifier = Modifier
                ) {
                    NavHost(
                        navController = navController,
                        startDestination = PhotoListScreen,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        composable<PhotoListScreen> {
                            PhotoListScreenFlow(
                                animatedVisibilityScope = this@composable
                            ) {
                                navController.navigate(FullPhotoScreen(it.id))
                            }
                        }
                        composable<FullPhotoScreen> {
                            val args = it.toRoute<FullPhotoScreen>()
                            FullPhotoScreenFlow(
                                animatedVisibilityScope = this@composable,
                                photoId = args.photoId
                            ) {
                                navController.navigateUp()
                            }
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
