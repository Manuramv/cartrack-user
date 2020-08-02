package com.cartrack.users.ui.home.userdetail

import android.util.Log
import androidx.lifecycle.ViewModel
import com.cartrack.users.data.model.UserListResponseItem

class UserDetailsViewModel: ViewModel() {
    val TAG = UserDetailsViewModel::class.java.canonicalName
    fun start(it: UserListResponseItem) {
        Log.d(TAG,"values::"+it.name)
    }
}