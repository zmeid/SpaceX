package com.zmeid.spacex.di.module

import android.app.Activity
import com.zmeid.spacex.view.ui.MainActivity
import dagger.Binds
import dagger.Module

/**
 * This module provides Activity to be used as context. For ex in [UtilsModule].
 */
@Module
abstract class MainActivityModule {
    @Binds
    abstract fun providesActivity(activity: MainActivity): Activity
}