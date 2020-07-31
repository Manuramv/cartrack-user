package com.cartrack.users.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cartrack.users.data.repository.LoginRepository


class ViewModelFactory (private val loginRepository: LoginRepository) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SplashViewModel(loginRepository) as T
    }

}