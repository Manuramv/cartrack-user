package com.cartrack.users.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.cartrack.users.data.entities.User

@Database(entities = [User::class], version = 1, exportSchema = true)
abstract class AppDataBase :RoomDatabase() {
    abstract fun loginDao(): LoginDao

    companion object{
        @Volatile private var instance: AppDataBase?=null

        fun getDataBase(ctx: Context):AppDataBase {
            return instance ?: synchronized(this) { instance ?: buildDatabase(ctx).also { instance = it } }
        }

        private fun buildDatabase(ctx: Context): AppDataBase {
            return Room.databaseBuilder(ctx,AppDataBase::class.java,"users").build()

        }

    }
}