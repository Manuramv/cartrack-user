package com.cartrack.users.ui.home.userlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cartrack.users.data.repository.UserRepository


//Since we are not using Dagger or anything - I'm passing the repository instance via Viewmodel factory.
/*class ViewModelFactory (private val remoteDataSource: RemoteDataSource) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return UserListViewModel(remoteDataSource) as T
    }

}*/

class ViewModelFactory<T> (private val userRepository: UserRepository, private val classType: Class<T>) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>) =
        with(modelClass){
            when {
                isAssignableFrom(UserListViewModel::class.java) -> UserListViewModel(userRepository)
                else -> throw IllegalArgumentException("Unknown viewModel class $modelClass")
            }
        } as T
}