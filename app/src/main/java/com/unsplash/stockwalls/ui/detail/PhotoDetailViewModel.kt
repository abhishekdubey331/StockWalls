package com.unsplash.stockwalls.ui.detail

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.unsplash.stockwalls.domain.contract.usecase.FetchPhotoByIdUseCase
import com.unsplash.stockwalls.ui.mapper.PhotoUIModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

sealed interface PhotoDetailViewState {
    object Loading : PhotoDetailViewState
    data class Success(val photo: PhotoUIModel) : PhotoDetailViewState
    data class Error(val message: String) : PhotoDetailViewState
}

@HiltViewModel
class PhotoDetailViewModel @Inject constructor(
    private val fetchPhotoByIdUseCase: FetchPhotoByIdUseCase
) : ViewModel() {

    private val _photoDetailsState = MutableStateFlow<PhotoDetailViewState>(
        PhotoDetailViewState.Loading
    )
    val photoDetailsState: StateFlow<PhotoDetailViewState> = _photoDetailsState

    fun getPhotoDetails(photoId: String) {
        viewModelScope.launch {
            fetchPhotoByIdUseCase(photoId).catch {
                _photoDetailsState.value = PhotoDetailViewState.Error(
                    "Error fetching photo details"
                )
                Log.e(TAG, "getPhotoDetails: Error fetching photo details $it")
            }.collectLatest { photo ->
                _photoDetailsState.value = PhotoDetailViewState.Success(photo)
            }
        }
    }

    companion object {
        private const val TAG = "PhotoDetailViewModel"
    }
}
