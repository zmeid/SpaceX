package com.zmeid.spacex.repository

import com.zmeid.spacex.repository.webservice.LaunchService
import javax.inject.Inject

/**
 * [LaunchRepository] is responsible to supply data to [com.zmeid.spacex.viewmodel.MainActivityViewModel].
 *
 * [LaunchService] is injected here.
 */
class LaunchRepository @Inject constructor(private val launchService: LaunchService) {
    suspend fun getLaunches() = launchService.getLaunches()
}