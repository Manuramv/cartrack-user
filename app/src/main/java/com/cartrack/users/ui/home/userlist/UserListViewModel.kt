package com.cartrack.users.ui.home.userlist

import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.cartrack.users.data.repository.UserRepository
import com.cartrack.users.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class UserListViewModel(private val userRepository: UserRepository) : ViewModel() {

    fun abc(){
      //  remoteDataSource.getUsersList()
        GlobalScope.launch(Dispatchers.IO) {
            Log.d("UserListViewModel","response=="+userRepository.getUser())
            userRepository.getUser().let {
                when(it.status){
                    Resource.Status.NETWORK_CALL_SUCCESS ->{
                        Log.d("UserListViewModel","response== api success")
                    }
                    Resource.Status.ERROR ->{
                        Log.d("UserListViewModel","response== api error"+it)
                    }
                    else -> {

                    }
                }
            }

        }
    }


}