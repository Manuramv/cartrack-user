package com.cartrack.users.ui.splash

import android.content.Context
import android.util.Log
import androidx.lifecycle.*
import com.cartrack.users.data.entities.User
import com.cartrack.users.data.repository.LoginRepository
import com.cartrack.users.utils.JsonParser.getJsonDataFromAsset
import com.cartrack.users.utils.Resource
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.launch
import java.lang.Exception

class SplashViewModel(private val loginRepository: LoginRepository) : ViewModel() {
    private lateinit var _insertObserver :LiveData<Boolean>
    var insertObserver = MutableLiveData<Boolean>()
    val userList =  arrayListOf<User>()


    //Read the json from asset folder and update to database
    fun readUserJsonandInsertToDb(context:Context){
        viewModelScope.launch {
            val jsonFileString = getJsonDataFromAsset(context, "userlist.json")
            val gson = Gson()
            val listPersonType = object : TypeToken<UserAccountDetails>() {}.type
            var persons: UserAccountDetails = gson.fromJson(jsonFileString, listPersonType)
            persons.user.forEachIndexed {
                    idx, person -> userList.add(person)
            }
            _insertObserver = loginRepository?.insertNewUser(userList)!!
            insertObserver.value = _insertObserver.value
        }
    }
}