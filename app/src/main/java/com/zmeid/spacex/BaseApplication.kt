package com.zmeid.spacex

import com.zmeid.spacex.di.component.DaggerAppComponent
import com.zmeid.spacex.util.TimberLineNumberDebugTree
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import timber.log.Timber
import javax.inject.Inject

/**
 * [BaseApplication] class exist for the life time of application. It holds [Timber] plant configuration and [DaggerAppComponent] application level injection.
 */
class BaseApplication : DaggerApplication() {
    @Inject
    lateinit var timberLineNumberDebugTree: TimberLineNumberDebugTree

    override fun onCreate() {
        super.onCreate()

        Timber.plant(timberLineNumberDebugTree)
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().application(this).build()
    }
}