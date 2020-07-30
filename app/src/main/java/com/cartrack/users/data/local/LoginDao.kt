package com.cartrack.users.data.local

import androidx.room.Dao
import androidx.room.Insert
import com.cartrack.users.data.entities.User

//Quries will go here
@Dao
interface LoginDao {

    @Insert
    suspend fun insert(user : User)
}