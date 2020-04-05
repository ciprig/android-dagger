package com.example.android.dagger.di

import android.content.Context
import com.example.android.dagger.login.LoginComponent
import com.example.android.dagger.registration.RegistrationComponent
import com.example.android.dagger.registration.enterdetails.EnterDetailsViewModel
import com.example.android.dagger.splash.SplashActivity
import com.example.android.dagger.user.UserManager
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [StorageModule::class, AppSubcomponents::class])
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    val userManager: UserManager

    //val userComponentFactory: UserComponent.Factory
    fun registrationComponent(): RegistrationComponent
    fun loginComponent(): LoginComponent

    fun enterDetailsViewModel(): EnterDetailsViewModel

    fun inject(mainActivity: SplashActivity)
}