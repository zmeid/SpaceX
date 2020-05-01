package com.zmeid.spacex.repository.webservice

import com.zmeid.spacex.model.Launch
import com.zmeid.spacex.util.GET_PATH_LAUNCHES_ORDERED_BY_DATE_DESC
import retrofit2.http.GET

/**
 * Holds launch service calls which will be used in [com.zmeid.spacex.repository.LaunchRepository]
 */
interface LaunchService {
    @GET(GET_PATH_LAUNCHES_ORDERED_BY_DATE_DESC)
    suspend fun getLaunches(): List<Launch>
}