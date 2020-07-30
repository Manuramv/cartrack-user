package com.cartrack.users.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "usertable")
data class User (
    @PrimaryKey
    val id: Int,
    val userName: String,
    val password: String
)
