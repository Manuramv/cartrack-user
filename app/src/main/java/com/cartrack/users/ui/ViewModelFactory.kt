package com.cartrack.users.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cartrack.users.data.repository.LoginRepository
import com.cartrack.users.ui.login.LoginViewModel
import com.cartrack.users.ui.splash.SplashViewModel


class ViewModelFactory<T> (private val loginRepository: LoginRepository, private val classType: Class<T>) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>) =
        with(modelClass){
            when {
                isAssignableFrom(SplashViewModel::class.java) -> SplashViewModel(loginRepository)
                isAssignableFrom(LoginViewModel::class.java) -> LoginViewModel(loginRepository)
                else -> throw IllegalArgumentException("Unknown viewModel class $modelClass")
            }
        } as T


   /* companion object {
        private var instance : ViewModelFactory? = null
        fun getInstance() =
            instance ?: synchronized(ViewModelFactory::class.java){
                instance ?: ViewModelFactory().also { instance = it }
            }
    }*/

}