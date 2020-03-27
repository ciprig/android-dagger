package com.example.android.dagger.di

import androidx.lifecycle.ViewModel

object ViewModelModule {

    inline fun <reified T : ViewModel> provideViewModel(factory: ViewModelFactory<T>) {
        factory.create(T::class.java)
    }
}