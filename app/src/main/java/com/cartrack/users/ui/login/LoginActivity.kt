package com.cartrack.users.ui.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.cartrack.users.R
import com.cartrack.users.data.repository.LoginRepositoryFactory
import com.cartrack.users.ui.ViewModelFactory
import com.cartrack.users.ui.splash.SplashViewModel
import com.cartrack.users.utils.Resource
import kotlinx.android.synthetic.main.activity_login.*
import kotlin.math.log

class LoginActivity : AppCompatActivity() {
    private val TAG = LoginActivity::class.java.canonicalName
    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginViewModel = ViewModelProvider(this, ViewModelFactory(LoginRepositoryFactory.createLoginRepository(application), SplashViewModel::class.java ))
            .get(LoginViewModel::class.java)


        btnLogin.setOnClickListener({
            loginViewModel.performLogin(etUserName.text.toString(), etPassword.text.toString())?.observe(this, Observer {
                when(it.status){
                    Resource.Status.SUCCESS ->{
                        Log.d(TAG,"Login success::"+it.data)
                    }

                    Resource.Status.ERROR ->{
                        Log.d(TAG,"Login error::"+it.message)
                    }
                    Resource.Status.LOADING ->{
                        Log.d(TAG,"Login loading::"+it.message)
                    }

                }
                })
        })
    }
}
