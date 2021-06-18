package com.abhishek.daggerhilt.di

import com.abhishek.daggerhilt.data.UserResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {

    @GET("dd7fae26-4511-4dbc-a93b-8b03b8686ecf")
    suspend fun getUsers(): Response<UserResponse>
}