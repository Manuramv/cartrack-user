package com.cartrack.users.data.repository

import android.app.Application

object LoginRepositoryFactory {
    fun createLoginRepository(application: Application) : LoginRepository {
        return LoginRepository(application)
    }
}