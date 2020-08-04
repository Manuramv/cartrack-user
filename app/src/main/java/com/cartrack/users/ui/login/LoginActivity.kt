package com.cartrack.users.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.cartrack.users.R
import com.cartrack.users.data.repository.LoginRepositoryFactory
import com.cartrack.users.databinding.ActivityLoginBinding
import com.cartrack.users.ui.ViewModelFactory
import com.cartrack.users.ui.home.HomeActivity
import com.cartrack.users.ui.splash.SplashViewModel
import com.cartrack.users.utils.Resource
import kotlinx.android.synthetic.main.activity_login.*
import kotlin.math.log

class LoginActivity : AppCompatActivity() {
    private val TAG = LoginActivity::class.java.canonicalName
    private lateinit var loginViewModel: LoginViewModel
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityLoginBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_login)

        loginViewModel = ViewModelProvider(this, ViewModelFactory(LoginRepositoryFactory.createLoginRepository(application), SplashViewModel::class.java ))
            .get(LoginViewModel::class.java)

        binding.lifecycleOwner = this
        binding.viewModel = loginViewModel
        loginViewModel.readCountryJson(this)


        loginViewModel.user?.observe(this, Observer {
                when(it.status){
                    Resource.Status.SUCCESS ->{
                        navigateToHomeScreen()
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
    }



    private fun navigateToHomeScreen(){
        startActivity(Intent(this, HomeActivity::class.java))
    }
}
