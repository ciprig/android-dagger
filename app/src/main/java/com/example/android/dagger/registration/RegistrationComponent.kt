package com.example.android.dagger.registration

import com.example.android.dagger.di.ActivityScope
import dagger.Subcomponent

@Subcomponent
@ActivityScope
interface RegistrationComponent {
    fun registrationViewModel(): RegistrationViewModel
}