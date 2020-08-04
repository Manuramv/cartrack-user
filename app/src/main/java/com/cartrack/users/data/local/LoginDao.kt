package com.cartrack.users.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cartrack.users.data.entities.User
import io.reactivex.rxjava3.core.Completable

//Quries will go here
@Dao
interface LoginDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(user : List<User>): List<Long>

    @Query("SELECT * FROM USERTABLE WHERE userName =:userName and password= :password")
    fun getUser(userName: String, password:String ) :LiveData<User>

    @Query("SELECT * FROM USERTABLE")
    fun getAllUsers( ) :LiveData<List<User>>


}