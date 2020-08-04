package com.cartrack.users.ui.splash

import com.cartrack.users.data.entities.User

data class UserAccountDetails(
    val user: List<User>
)


data class User(
    val id: Int,
    val password: String,
    val userName: String
)

