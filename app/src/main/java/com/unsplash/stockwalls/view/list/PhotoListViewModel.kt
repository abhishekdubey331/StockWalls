package com.unsplash.stockwalls.view.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.unsplash.stockwalls.data.UnsplashPhoto
import com.unsplash.stockwalls.repository.MainRepository
import com.unsplash.stockwalls.utils.DispatcherProvider
import com.unsplash.stockwalls.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PhotoListViewModel @Inject constructor(
    private val repository: MainRepository,
    private val dispatchers: DispatcherProvider,
) : ViewModel() {

    sealed class PhotoFetchEvent {
        class Success(val unsplashPhoto: UnsplashPhoto?) : PhotoFetchEvent()
        class Failure(val errorText: String) : PhotoFetchEvent()
        object Loading : PhotoFetchEvent()
        object Empty : PhotoFetchEvent()
    }

    private val _photoFetchEvent = MutableStateFlow<PhotoFetchEvent>(PhotoFetchEvent.Empty)
    val photoFetchEvent: StateFlow<PhotoFetchEvent> = _photoFetchEvent
    var currentPage = 1

    fun fetchPhotosByPage(pageNo: Int) {
        viewModelScope.launch(dispatchers.io) {
            _photoFetchEvent.value = PhotoFetchEvent.Loading
            currentPage++
            when (val photoResponse = repository.getPhotoList(pageNo)) {
                is Resource.Error -> {
                    _photoFetchEvent.value =
                        PhotoFetchEvent.Failure(photoResponse.message ?: "Something Went Wrong")
                }
                is Resource.Success -> {
                    val photoList = photoResponse.data
                    _photoFetchEvent.value = PhotoFetchEvent.Success(photoList)
                }
            }
        }
    }
}