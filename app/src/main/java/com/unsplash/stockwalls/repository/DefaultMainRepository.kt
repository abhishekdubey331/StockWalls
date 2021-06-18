package com.abhishek.daggerhilt.repository

import com.abhishek.daggerhilt.data.UserResponse
import com.abhishek.daggerhilt.di.ApiInterface
import com.abhishek.daggerhilt.utils.Resource
import javax.inject.Inject

class DefaultMainRepository @Inject constructor(
    private val currencyApi: ApiInterface
) : MainRepository {

    override suspend fun getUsers(): Resource<UserResponse> {
        return try {
            val response = currencyApi.getUsers()
            val result = response.body()
            if (response.isSuccessful && result != null) {
                Resource.Success(result)
            } else {
                Resource.Error(response.message())
            }
        } catch (e: Exception) {
            Resource.Error(e.message ?: "An error occured")
        }
    }
}