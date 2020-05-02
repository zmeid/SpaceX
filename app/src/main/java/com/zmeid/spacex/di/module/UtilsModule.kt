package com.zmeid.spacex.di.module

import android.app.Activity
import android.app.Application
import androidx.recyclerview.widget.LinearLayoutManager
import com.squareup.picasso.Picasso
import com.zmeid.spacex.R
import com.zmeid.spacex.util.ErrorMessageGenerator
import dagger.Module
import dagger.Provides

/**
 * Provides utility objects.
 */
@Module
class UtilsModule {
    @Provides
    fun providesLayoutManager(context: Activity) = LinearLayoutManager(context)

    @Provides
    fun providesApiErrorMessageGenerator(context: Activity) = ErrorMessageGenerator(context)
}