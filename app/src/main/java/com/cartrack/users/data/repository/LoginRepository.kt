package com.cartrack.users.data.repository

import android.app.Application
import android.content.res.Resources
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import com.cartrack.users.data.entities.User
import com.cartrack.users.data.local.AppDataBase
import com.cartrack.users.data.local.LoginDao
import com.cartrack.users.utils.Resource
import kotlinx.coroutines.Dispatchers


class LoginRepository (application: Application) {
    private var mLoginDao: LoginDao? = null
    private val user:LiveData<User>?=null
    init {
        val db = AppDataBase.getDataBase(application)
        mLoginDao = db.loginDao()
        //user = mLoginDao.getUser()
    }

    //fun insertUser(user:User) =

    ///function to get the user..
    fun getUser(userName: String, pwd:String) = getData( databaseQuery = {mLoginDao?.getUser(userName, pwd)})


    fun <T> getData(databaseQuery: () -> LiveData<T>?): LiveData<Resource<T>>? {
        return liveData(Dispatchers.IO) {
            emit(Resource.loading())
            val source = databaseQuery.invoke()?.map { Resource.success(it) }

            if(source!=null)
            emitSource(source)
            else{
                emit(Resource.error("We couldn't find you in our database. Please check your credentials..."))
                emitSource(source!!)
            }


        }
    }

    fun testMethod(){
        Log.d("tag","called test method")
    }

}