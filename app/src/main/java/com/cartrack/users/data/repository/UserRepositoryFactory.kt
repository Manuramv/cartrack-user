package com.cartrack.users.data.repository

import com.cartrack.users.data.remote.ApiInterface
import com.cartrack.users.data.remote.RemoteDataSource
import com.cartrack.users.data.remote.RetrofitApiClient

object UserRepositoryFactory {
    fun createCurrecncyRepository() : RemoteDataSource {
        return RemoteDataSource(
            RetrofitApiClient.instance.buildService(
                ApiInterface::class.java
            )
        )
    }
}