package com.cartrack.users.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.cartrack.users.data.entities.User
import com.cartrack.users.data.repository.LoginRepository
import com.cartrack.users.utils.Resource

class SplashViewModel(loginRepository: LoginRepository) : ViewModel() {
    private val TAG = SplashViewModel::class.java.canonicalName
    private val _userName = MutableLiveData<String>()
    private val _pwd = MutableLiveData<String>()

    fun performLogin(userName: String, pwd:String){
        _userName.value = userName
        _pwd.value = pwd
    }
    init {
        Log.d(TAG,"in viewmodel init::"+loginRepository)
        loginRepository.testMethod()
    }




    /*private val _user = loginRepository.getUser(_userName.value!!,_pwd.value!!)

    val user: LiveData<Resource<User>> = _user!!*/


}