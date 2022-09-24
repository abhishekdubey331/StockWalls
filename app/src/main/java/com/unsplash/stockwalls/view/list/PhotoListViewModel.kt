package com.unsplash.stockwalls.view.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.unsplash.stockwalls.common.ResultState
import com.unsplash.stockwalls.data.UnsplashPhotoItem
import com.unsplash.stockwalls.domain.contract.FetchPhotosUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
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

    var currentPage = 1

    private val _photoFetchEvent = MutableStateFlow(PhotoListScreenState())
    val photoFetchEvent = _photoFetchEvent.asStateFlow()

    init {
        fetchPhotosByPage(1)
    }

    fun fetchPhotosByPage(pageNo: Int) {
        viewModelScope.launch {
            fetchPhotosUseCase(pageNo).collect { result ->
                _photoFetchEvent.update {
                    when (result) {
                        is ResultState.Loading -> it.copy(loading = true, errorMessage = "")

                        is ResultState.Success -> it.copy(loading = false, photosList = result.data)

                        is ResultState.Failure -> it.copy(
                            loading = false,
                            errorMessage = result.errorMessage
                        )
                    }
                }
            }
        }
    }
}