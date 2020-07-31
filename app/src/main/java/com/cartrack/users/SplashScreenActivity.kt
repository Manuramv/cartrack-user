package com.cartrack.users

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.cartrack.users.data.repository.LoginRepository
import com.cartrack.users.data.repository.LoginRepositoryFactory
import com.cartrack.users.ui.SplashViewModel
import com.cartrack.users.ui.ViewModelFactory
import com.cartrack.users.utils.Resource

class SplashScreenActivity : AppCompatActivity() {
    val TAG = SplashScreenActivity::class.java.canonicalName
    private lateinit var splashViewModel: SplashViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        splashViewModel = ViewModelProvider(this, ViewModelFactory(LoginRepositoryFactory.createLoginRepository(application)))
            .get(SplashViewModel::class.java)

        /*splashViewModel.user.observe(this, Observer {
            when(it.status){
                Resource.Status.SUCCESS ->{
                    Log.d(TAG,"db query success::"+it.data)
                }

                Resource.Status.ERROR ->{
                    Log.d(TAG,"db query error::"+it.message)
                }

            }
        })*/

    }
}
