package com.cartrack.users.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.cartrack.users.data.entities.User
import com.cartrack.users.data.repository.LoginRepository
import com.cartrack.users.utils.Resource

class LoginViewModel(val loginRepository: LoginRepository) :ViewModel() {
    private  var _user : LiveData<Resource<User>>?=null
    lateinit var user: LiveData<Resource<User>>

    /*fun performLogin(userName: String, password: String) {
        _user = loginRepository.getUser(userName, password)
        user = _user!!
    }*/

    fun performLogin(userName: String, password: String) = loginRepository.getUser(userName, password)
}