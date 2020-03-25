package com.example.android.dagger.splash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.android.dagger.MyApplication
import com.example.android.dagger.login.LoginActivity
import com.example.android.dagger.main.MainActivity
import com.example.android.dagger.registration.RegistrationActivity
import com.example.android.dagger.user.UserManager
import javax.inject.Inject

class SplashActivity : AppCompatActivity() {

    //field injection even this is only used in onCreate
    //cannot be private cannot be final/val
    @Inject
    protected lateinit var userManager: UserManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Call before super.onCreate() to avoid issues with fragment restoration.
        // In super.onCreate, an Activity during the restore phase will attach fragments that might
        // want to access activity bindings.
        (application as MyApplication).appComponent.inject(this)

        if (!userManager.isUserLoggedIn()) {
            if (!userManager.isUserRegistered()) {
                startActivity(Intent(this, RegistrationActivity::class.java))
            } else {
                startActivity(Intent(this, LoginActivity::class.java))
            }
        } else {
            startActivity(Intent(this, MainActivity::class.java))
        }
        finish()
    }
}