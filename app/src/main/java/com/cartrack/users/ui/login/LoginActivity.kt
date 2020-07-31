package com.cartrack.users.ui.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.cartrack.users.R
import com.cartrack.users.data.repository.LoginRepositoryFactory
import com.cartrack.users.ui.ViewModelFactory
import com.cartrack.users.ui.splash.SplashViewModel

class LoginActivity : AppCompatActivity() {
    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginViewModel = ViewModelProvider(this, ViewModelFactory(LoginRepositoryFactory.createLoginRepository(application), SplashViewModel::class.java ))
            .get(LoginViewModel::class.java)


    }
}
