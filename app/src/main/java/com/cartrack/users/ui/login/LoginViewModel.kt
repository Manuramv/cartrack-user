package com.cartrack.users.ui.login

import android.text.TextUtils
import android.util.Log
import android.util.Patterns
import androidx.lifecycle.*
import com.cartrack.users.data.entities.User
import com.cartrack.users.data.repository.LoginRepository
import com.cartrack.users.utils.Resource
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginViewModel(private val loginRepository: LoginRepository) :ViewModel() {
    var emailAddress= MutableLiveData<String>()
    var password = MutableLiveData<String>()
    private val _id = MutableLiveData<Int>()
   // private val _user = loginRepository.getUser("user1", "password@18")
    private val _user = _id.switchMap { id ->
        loginRepository.getUser(emailAddress.value!!, password.value!!)!!
    }

    val user: LiveData<Resource<User>> = _user!!


    /* fun performLogin()  {
         user = loginRepository.getUser("user1", "password@18")
         user =null
     }*/

   fun performLogin() {
       _id.value = _id.value?.plus(1);
   }


}