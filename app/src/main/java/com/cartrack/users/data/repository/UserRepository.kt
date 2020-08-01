package com.cartrack.users.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import com.cartrack.users.data.remote.RemoteDataSource
import com.cartrack.users.utils.Resource
import com.cartrack.users.utils.Resource.Status.ERROR
import com.cartrack.users.utils.Resource.Status.SUCCESS
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.invoke
import kotlinx.coroutines.launch
import java.lang.Exception

class UserRepository() {
    lateinit var remoteDataSource: RemoteDataSource //= UserRepositoryFactory.createCurrecncyRepository()

    init {
        remoteDataSource = UserRepositoryFactory.createCurrecncyRepository()
        //fun getUsersList() = getData({remoteDataSource.getUsersList() })
        Log.d("tag", "remote data source::" + remoteDataSource)

    }


    suspend fun getUser() = getData( networkCall = {remoteDataSource.getUsersList()})

    suspend fun <T> getData(networkCall: suspend () -> Resource<T>): Resource<T> {
        val result = networkCall.invoke()
        return when (result.status) {
             SUCCESS -> {
                 Resource.success(result.data!!)
             }
            ERROR -> {
                Resource.error(result.message!!)
            }
            else -> Resource.error(result.message!!)
        }
    }

}
