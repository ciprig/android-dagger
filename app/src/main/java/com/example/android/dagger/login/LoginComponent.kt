package com.example.android.dagger.login

import com.example.android.dagger.di.ActivityScope
import com.example.android.dagger.di.ViewModelFactory
import dagger.Subcomponent

@ActivityScope
@Subcomponent
interface LoginComponent {

    val loginViewModelFactory: ViewModelFactory<LoginViewModel>
    fun loginViewModel(): LoginViewModel
}