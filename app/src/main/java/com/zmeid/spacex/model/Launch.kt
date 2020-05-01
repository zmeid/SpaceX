package com.zmeid.spacex.model

import com.google.gson.annotations.SerializedName
import com.zmeid.spacex.util.*
import java.util.*

/**
 * [Launch] data class is used by Retrofit. It holds variables needed to map API responses.
 *
 */
data class Launch(
    val flightNumber: Int,
    @SerializedName(SN_MISSION_NAME) val missionName: String,
    @SerializedName(SN_ROCKET) val rocket: Rocket,
    @SerializedName(SN_LAUNCH_SITE) val launchSite: LaunchSite,
    @SerializedName(LAUNCH_DATE_UNIX) val launchDate: Date,
    @SerializedName(SN_LINKS) val launchLink: LaunchLink
)