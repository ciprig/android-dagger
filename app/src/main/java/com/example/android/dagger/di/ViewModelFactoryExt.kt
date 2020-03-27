package com.example.android.dagger.di

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.android.dagger.appComponent

@Suppress("UNCHECKED_CAST")
inline fun <reified T : ViewModel> FragmentActivity.getViewModel(crossinline factory: () -> T) =
    T::class.java.let {
        ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>) = factory() as T
        }).get(it)
    }

inline fun <reified T : ViewModel> viewModelOf(
    activity: FragmentActivity,
    crossinline initializer: () -> T
) =
    lazy(LazyThreadSafetyMode.NONE) { activity.getViewModel { initializer() } }


inline fun <reified T : ViewModel> FragmentActivity.viewModelInjector(crossinline initializer: AppComponent.() -> T) =
    lazy(LazyThreadSafetyMode.NONE) {
        getViewModel { appComponent.run(initializer) }
    }
