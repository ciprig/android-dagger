package com.example.android.dagger.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.android.dagger.user.UserDataRepository
import dagger.Reusable
import javax.inject.Inject

@Reusable
class MainViewModelFactory @Inject constructor(private val userDataRepository: UserDataRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(userDataRepository) as T
    }
}