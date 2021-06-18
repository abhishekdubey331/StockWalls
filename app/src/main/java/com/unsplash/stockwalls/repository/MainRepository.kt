package com.abhishek.daggerhilt.repository

import com.abhishek.daggerhilt.data.UserResponse
import com.abhishek.daggerhilt.utils.Resource

interface MainRepository {
    suspend fun getUsers(): Resource<UserResponse>
}