package com.zmeid.spacex.di.component

import android.app.Application
import com.zmeid.spacex.BaseApplication
import com.zmeid.spacex.di.module.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * AppComponent exist for the life time of application. It has the list of modules which are going to be used by application.
 */
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ViewModuleFactoryModule::class,
        ActivitiesModule::class,
        WebServiceModule::class,
        MainActivityModule::class,
        UtilsModule::class
    ]
)
@Singleton
interface AppComponent : AndroidInjector<BaseApplication> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}