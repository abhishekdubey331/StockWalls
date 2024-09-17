package com.unsplash.stockwalls.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.unsplash.stockwalls.common.ResultState
import com.unsplash.stockwalls.data.model.UnsplashPhotoItemDto
import com.unsplash.stockwalls.domain.contract.FetchPhotosUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class PhotoListScreenState(
    val loading: Boolean = false,
    val photosList: List<UnsplashPhotoItemDto> = emptyList(),
    val errorMessage: String? = null
)

@HiltViewModel
class PhotoListViewModel @Inject constructor(
    private val fetchPhotosUseCase: FetchPhotosUseCase
) : ViewModel() {

    private val _photoListUiState = MutableStateFlow(PhotoListScreenState())
    val photoListUiState: StateFlow<PhotoListScreenState> = _photoListUiState

    init {
        fetchPhotos(0)
    }

    private fun fetchPhotos(page: Int) {
        viewModelScope.launch {
            fetchPhotosUseCase(page).collect { result ->
                _photoListUiState.value = when (result) {
                    is ResultState.Loading -> _photoListUiState.value.copy(
                        loading = true,
                        errorMessage = ""
                    )

                    is ResultState.Success -> _photoListUiState.value.copy(
                        loading = false,
                        photosList = result.data
                    )

                    is ResultState.Failure -> _photoListUiState.value.copy(
                        loading = false,
                        errorMessage = result.errorMessage
                    )
                }
            }
        }
    }
}
