package com.example.android.dagger.util

import androidx.activity.viewModels
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

inline fun <reified T : ViewModel> FragmentActivity.getViewModel(
    crossinline provider: () -> T
) = ViewModelProvider(this, object : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        provider() as T
}).get(T::class.java)


inline fun <reified T : ViewModel> FragmentActivity.viewModel(
    crossinline provider: () -> T
) = lazy(LazyThreadSafetyMode.NONE) {
    getViewModel(provider)
}

inline fun <reified T : ViewModel> FragmentActivity.viewModel2(
    crossinline provider: () -> T
) = viewModels<T> {
    object : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T =
            provider() as T
    }
}
