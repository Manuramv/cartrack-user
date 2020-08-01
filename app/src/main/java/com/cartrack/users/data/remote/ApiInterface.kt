package com.cartrack.users.data.remote


import com.cartrack.users.data.model.UserListResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {
    @GET("users")
    suspend fun getUsersList(): Response<UserListResponse>
}
