package com.example.android.dagger.di

import com.example.android.dagger.storage.SharedPreferencesStorage
import com.example.android.dagger.storage.Storage
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@InstallIn(SingletonComponent::class)
@Module
abstract class StorageModule {
    @Binds
    abstract fun provideStorage(storage: SharedPreferencesStorage): Storage
//    companion object {
//        @JvmStatic
//        @Provides
//        fun provideStorage(storage: SharedPreferencesStorage): Storage = storage
//    }
}