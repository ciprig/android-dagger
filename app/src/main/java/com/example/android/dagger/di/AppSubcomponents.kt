package com.example.android.dagger.di

import com.example.android.dagger.user.UserComponent
import dagger.Module

@Module(subcomponents = [UserComponent::class])
class AppSubcomponents