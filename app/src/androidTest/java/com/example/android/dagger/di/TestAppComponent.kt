package com.example.android.dagger.di

import dagger.Component
import javax.inject.Singleton

@Component(modules = [TestStorageModule::class])
@Singleton
interface TestAppComponent : AppComponent