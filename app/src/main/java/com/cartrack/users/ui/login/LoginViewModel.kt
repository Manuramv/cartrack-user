package com.cartrack.users.ui.login

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cartrack.users.data.entities.User
import com.cartrack.users.data.repository.LoginRepository
import com.cartrack.users.utils.Resource

class LoginViewModel(val loginRepository: LoginRepository) :ViewModel() {
    private  var _user : LiveData<Resource<User>>?=null
    lateinit var user: LiveData<Resource<User>>
    var emailAddress= MutableLiveData<String>()

    /*fun performLogin(userName: String, password: String) {
        _user = loginRepository.getUser(userName, password)
        user = _user!!
    }*/

    fun performLogin(userName: String, password: String) = loginRepository.getUser(userName, password)

    fun isFormValid(emailAddress: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(emailAddress).matches()
    }

    val valid = MediatorLiveData<String>().apply {
        addSource(emailAddress) {
            if(isFormValid(it)){
                value =" "
            } else {
                value = "enter valid email"
            }
            //value = isFormValid(it)
        }
    }
}