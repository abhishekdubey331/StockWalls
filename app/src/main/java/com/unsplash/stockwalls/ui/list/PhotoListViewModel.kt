package com.unsplash.stockwalls.ui.list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.unsplash.stockwalls.common.ResultState
import com.unsplash.stockwalls.data.model.UnsplashPhotoItem
import com.unsplash.stockwalls.domain.contract.FetchPhotosUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

data class PhotoListScreenState(
    val loading: Boolean = false,
    val photosList: List<UnsplashPhotoItem> = emptyList(),
    val errorMessage: String? = null
)

@HiltViewModel
class PhotoListViewModel @Inject constructor(
    private val fetchPhotosUseCase: FetchPhotosUseCase
) : ViewModel() {

    private val _photoListUiState = mutableStateOf(PhotoListScreenState())
    val photoListUiState: State<PhotoListScreenState> = _photoListUiState

    init {
        fetchPhotos(0)
    }

    private fun fetchPhotos(page: Int) {
        viewModelScope.launch {
            fetchPhotosUseCase(page).collect { result ->
                _photoListUiState.value = when (result) {
                    is ResultState.Loading -> _photoListUiState.value.copy(
                        loading = true, errorMessage = ""
                    )

                    is ResultState.Success -> _photoListUiState.value.copy(
                        loading = false, photosList = result.data
                    )

                    is ResultState.Failure -> _photoListUiState.value.copy(
                        loading = false, errorMessage = result.errorMessage
                    )
                }
            }
        }
    }
}
