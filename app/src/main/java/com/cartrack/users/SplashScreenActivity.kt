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

        //read the json from asset folder and insert users to database
        splashViewModel.readUserJsonandInsertToDb(this)
        //setup the observer
        setUpObserver()


    }

    //observe for the view model changes
    private fun setUpObserver() {
        splashViewModel.insertObserver?.observe(this, Observer {
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
