package com.cartrack.users.data.repository

import android.app.Application
import android.util.Log
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import com.cartrack.users.data.entities.User
import com.cartrack.users.data.local.AppDataBase
import com.cartrack.users.data.local.LoginDao
import com.cartrack.users.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.invoke


class LoginRepository (application: Application) {
    private var mLoginDao: LoginDao? = null
    private val user:LiveData<User>?=null
    init {
        val db = AppDataBase.getDataBase(application)
        mLoginDao = db.loginDao()
    }


    suspend fun insert(user: User) {
        insertNewUserToDatabase(user)
    }


    ///function to get the user..
    fun getUser(userName: String, pwd:String) = getData( databaseQuery = {mLoginDao?.getUser(userName, pwd)})

    fun getAllUsers() = getData( databaseQuery = {mLoginDao?.getAllUsers()})


    private fun <T> getData(databaseQuery: () -> LiveData<T>?): LiveData<Resource<T>>? {
        return liveData(Dispatchers.IO) {
            emit(Resource.loading())
            val source = databaseQuery.invoke()?.map {
                if(it!=null)
                Resource.success(it)
                else
                    Resource.error("We couldn't find you in our database. Please check your credentials...")
            }
            emitSource(source!!)
        }
    }

   /* suspend fun <T> insertData(saveCallResult: suspend (T) -> Unit):Any{
        Dispatchers.IO({
            saveCallResult.invoke(T)
        })
    }
*/

    suspend fun insertNewUserToDatabase(user: User){
        Dispatchers.IO({
            mLoginDao?.insert(user)
        })
    }

    fun testMethod(){
        Log.d("tag","called test method")
    }

}