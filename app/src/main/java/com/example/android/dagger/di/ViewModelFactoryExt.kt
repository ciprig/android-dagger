package com.example.android.dagger.di

import androidx.activity.ComponentActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.example.android.dagger.appComponent

@Suppress("UNCHECKED_CAST")
inline fun <reified T : ViewModel> getViewModel(
    owner: ViewModelStoreOwner,
    crossinline factory: () -> T
) =
    T::class.java.let {
        ViewModelProvider(owner, object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>) = factory() as T
        }).get(it)
    }

inline fun <reified T : ViewModel> ComponentActivity.viewModelInjector(
    crossinline factory: AppComponent.() -> T
) =
    lazy(LazyThreadSafetyMode.NONE) {
        getViewModel(this) { appComponent.run(factory) }
    }

inline fun <reified T : ViewModel> Fragment.viewModelInjector(
    owner: ViewModelStoreOwner = this,
    crossinline factory: AppComponent.() -> T
) =
    lazy(LazyThreadSafetyMode.NONE) {
        getViewModel(owner) { requireContext().appComponent.run(factory) }
    }

inline fun <reified T : ViewModel> Fragment.parentViewModelInjector(
    crossinline factory: AppComponent.() -> T
) =
    lazy(LazyThreadSafetyMode.NONE) {
        getViewModel((this.parentFragment ?: requireActivity()) as ViewModelStoreOwner) {
            requireContext().appComponent.run(factory)
        }
    }


