package com.unsplash.stockwalls.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.unsplash.stockwalls.domain.contract.usecase.FetchPhotosUseCase
import com.unsplash.stockwalls.ui.mapper.PhotoUIModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

@HiltViewModel
class PhotoListViewModel @Inject constructor(
    fetchPhotosUseCase: FetchPhotosUseCase
) : ViewModel() {

    val photoPagingDataFlow: Flow<PagingData<PhotoUIModel>> =
        fetchPhotosUseCase()
            .cachedIn(viewModelScope)
}
