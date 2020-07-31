package com.cartrack.users.ui.splash

import android.util.Log
import androidx.lifecycle.*
import com.cartrack.users.data.entities.User
import com.cartrack.users.data.repository.LoginRepository
import com.cartrack.users.utils.Resource
import kotlinx.coroutines.launch
import java.lang.Exception

class SplashViewModel(loginRepository: LoginRepository) : ViewModel() {
    private val TAG = SplashViewModel::class.java.canonicalName
    private val _userName = MutableLiveData<String>()
    private val _pwd = MutableLiveData<String>()
     lateinit var _insertObserver :LiveData<Boolean>

    fun performLogin(userName: String, pwd:String){
        _userName.value = userName
        _pwd.value = pwd
    }
    init {
        Log.d(TAG,"in viewmodel init::"+loginRepository)
        loginRepository.testMethod()
        val user = User(1,"user1","password@18")
        val user2 = User(18,"user2","password@123")
        loginRepository?.insertNewUser(user)!!
        _insertObserver = loginRepository?.insertNewUser(user2)!!

    }




   // private val _user = loginRepository.getUser("_userName.value!!","_pwd.value!!")
    private val _user = loginRepository.getUser("manuram v","sreemanu")

    val user: LiveData<Resource<User>> = _user!!

    private val _allUsers = loginRepository.getAllUsers()
    val uallUsers: LiveData<Resource<List<User>>> = _allUsers!!

}