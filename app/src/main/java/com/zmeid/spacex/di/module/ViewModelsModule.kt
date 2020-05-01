package com.zmeid.spacex.di.module

import androidx.lifecycle.ViewModel
import com.zmeid.spacex.di.ViewModelKey
import com.zmeid.spacex.viewmodel.MainActivityViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Enables dependency injection for viewModels. New viewmodels should be added here with related activity.
 */
@Module
abstract class ViewModelsModule {
    @Binds
    @IntoMap
    @ViewModelKey(MainActivityViewModel::class)
    abstract fun bindMainActivityViewModel(mainActivityViewModel: MainActivityViewModel): ViewModel
}