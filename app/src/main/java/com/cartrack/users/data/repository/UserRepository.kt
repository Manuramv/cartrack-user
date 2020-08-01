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
    //suspend fun getUser() = remoteDataSource.getUsersList()


    /*private fun <A> getData(networkCall:  suspend (A) -> Unit): LiveData<Resource<T>>? {
        return liveData(Dispatchers.IO) {
            val responseStatus = networkCall.invoke()
            if (responseStatus.status == SUCCESS) {
                saveCallResult(responseStatus.data!!)

            } else if (responseStatus.status == ERROR) {
                emit(Resource.error("responseStatus.message!!"))
                emitSource(source)
            }
        }
    }*/


    suspend fun <T> getData(networkCall: suspend () -> Resource<T>): Resource<T> {
        var source:Resource<T>?=null
            val responseStatus = networkCall.invoke()
            if (responseStatus.status == SUCCESS) {
                source = Resource?.netWorkCallsuccess(responseStatus.data!!)
               // emit(source = source)
            } else if (responseStatus.status == ERROR) {
                // emit(Resource.error("responseStatus.message!!"))
                source = Resource.error(responseStatus.message!!)
            }
        return source!!
    }

}
