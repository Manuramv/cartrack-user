package com.cartrack.users

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.cartrack.users.data.repository.LoginRepositoryFactory
import com.cartrack.users.ui.splash.SplashViewModel
import com.cartrack.users.ui.ViewModelFactory
import com.cartrack.users.ui.login.LoginActivity
import com.cartrack.users.utils.Resource

class SplashScreenActivity : AppCompatActivity() {
    val TAG = SplashScreenActivity::class.java.canonicalName
    private lateinit var splashViewModel: SplashViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        splashViewModel = ViewModelProvider(this, ViewModelFactory(LoginRepositoryFactory.createLoginRepository(application),SplashViewModel::class.java ))
            .get(SplashViewModel::class.java)

        splashViewModel.user.observe(this, Observer {
            when(it.status){
                Resource.Status.SUCCESS ->{
                    Log.d(TAG,"db query success::"+it.data)
                }

                Resource.Status.ERROR ->{
                    Log.d(TAG,"db query error::"+it.message)
                }
                Resource.Status.LOADING ->{
                    Log.d(TAG,"db query Loading::"+it.message)
                }

            }
        })

        splashViewModel.uallUsers.observe(this, Observer {
            when(it.status){
                Resource.Status.SUCCESS ->{
                    Log.d(TAG,"db query uallUsers success::"+it.data)
                }

                Resource.Status.ERROR ->{
                    Log.d(TAG,"db query uallUsers error::"+it.message)
                }
                Resource.Status.LOADING ->{
                    Log.d(TAG,"db query uallUsers Loading::"+it.message)
                }

            }
        })


        splashViewModel._insertObserver?.observe(this, Observer {
            when(it){
                true -> navigateToLoginScreen()
                false -> "show error"
            }


        })


    }

    private fun navigateToLoginScreen(){
        startActivity(Intent(this, LoginActivity::class.java))
    }
}
