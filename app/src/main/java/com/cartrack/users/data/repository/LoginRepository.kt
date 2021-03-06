package com.cartrack.users.data.repository

import android.app.Application
import android.util.Log
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import com.cartrack.users.data.entities.User
import com.cartrack.users.data.local.AppDataBase
import com.cartrack.users.data.local.LoginDao
import com.cartrack.users.utils.Resource
import kotlinx.coroutines.*
import okhttp3.internal.concurrent.Task
import java.lang.Exception
import androidx.lifecycle.liveData as liveData1


class LoginRepository (application: Application) {
    private var mLoginDao: LoginDao? = null
    private val user:LiveData<User>?=null
    init {
        val db = AppDataBase.getDataBase(application)
        mLoginDao = db.loginDao()
    }

    fun insertNewUser(user: List<User>) = insertTask(user)



    ///function to get the user..
    fun getUser(userName: String, pwd:String) = getData( databaseQuery = {mLoginDao?.getUser(userName, pwd)})

    fun getAllUsers() = getData( databaseQuery = {mLoginDao?.getAllUsers()})


    private fun <T> getData(databaseQuery: () -> LiveData<T>?): LiveData<Resource<T>>? {
        return liveData1(Dispatchers.IO) {
            emit(Resource.loading())
            try {
                val source = databaseQuery.invoke()?.map {
                    if (it != null)
                        Resource.success(it)
                    else
                        Resource.error("We couldn't find you in our database. Please check your credentials...")
                }
                emitSource(source!!)
            } catch (e:Exception){
               // emitSource(Resource.error("hii"))
            }
        }
    }

   /* suspend fun <T> insertData(saveCallResult: suspend (T) -> Unit):Any{
        Dispatchers.IO({
            saveCallResult.invoke(T)
        })
    }
*/

    suspend fun<T> insertNewUserToDatabase(databaseQuery: () -> LiveData<T>?):LiveData<Resource<T>>?{
        return liveData1(Dispatchers.IO) {
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


    fun  insertTask(user: List<User>): MutableLiveData<Boolean> {
        val liveData = MutableLiveData<Boolean>()
        var insertSuccess = false;
        GlobalScope.launch(Dispatchers.IO) {
            val it = mLoginDao?.insertAll(user)
            Log.d("tag","inserted row=="+it)
            if(it!=null){
                Log.d("tag","inserted row== true=="+it)
                insertSuccess = true;
                GlobalScope.launch (Dispatchers.Main){
                    liveData.value = insertSuccess
                    Log.d("tag","inserted row== liveData.value =="+liveData.value )
                }
            } else {
                Log.d("tag","inserted row== false=="+it)
                insertSuccess = false
            }

        }



        return liveData
    }


    fun testMethod(){
        Log.d("tag","called test method")
    }

}