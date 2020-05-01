package com.zmeid.spacex.di.module

import com.zmeid.spacex.view.ui.LaunchDetailsActivity
import com.zmeid.spacex.view.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Defines activities where dependencies are going to be injected. New activities should be added here.
 */
@Module
abstract class ActivitiesModule {
    @ContributesAndroidInjector(modules = [ViewModelsModule::class, MainActivityModule::class, UtilsModule::class])
    abstract fun contributeMainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun contributeLaunchDetailsActivity(): LaunchDetailsActivity
}