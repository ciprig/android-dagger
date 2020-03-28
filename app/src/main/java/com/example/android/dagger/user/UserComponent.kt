package com.example.android.dagger.user

import com.example.android.dagger.main.MainViewModel
import com.example.android.dagger.settings.SettingsViewModel
import dagger.BindsInstance
import dagger.Subcomponent

@UserScope
@Subcomponent
interface UserComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(@UserName @BindsInstance username: String): UserComponent
    }

    fun settingsViewModel(): SettingsViewModel
    fun mainViewModel(): MainViewModel

    //   val  mainViewModelProvider: Provider<MainViewModel>
//    @UserName
//    fun getUserName(): String
}