package com.abhishek.daggerhilt.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abhishek.daggerhilt.data.User
import com.abhishek.daggerhilt.repository.MainRepository
import com.abhishek.daggerhilt.utils.DispatcherProvider
import com.abhishek.daggerhilt.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: MainRepository,
    private val dispatchers: DispatcherProvider,
): ViewModel() {

    sealed class UserFetchEvent {
        class Success(val user: User?) : UserFetchEvent()
        class Failure(val errorText: String) : UserFetchEvent()
        object Loading : UserFetchEvent()
        object Empty : UserFetchEvent()
    }

    private val _userFetchEvent = MutableStateFlow<UserFetchEvent>(UserFetchEvent.Empty)
    val userFetchEvent: StateFlow<UserFetchEvent> = _userFetchEvent

    fun fetchUsers() {
        viewModelScope.launch(dispatchers.io) {
            _userFetchEvent.value = UserFetchEvent.Loading
            when (val userResponse = repository.getUsers()) {
                is Resource.Error -> {
                    _userFetchEvent.value =
                        UserFetchEvent.Failure(userResponse.message ?: "Something Went Wrong")
                }
                is Resource.Success -> {
                    val user = userResponse.data?.data?.firstOrNull()
                    _userFetchEvent.value = UserFetchEvent.Success(user)
                }
            }
        }
    }
}