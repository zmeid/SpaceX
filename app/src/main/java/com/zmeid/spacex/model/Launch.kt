package com.zmeid.spacex.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.zmeid.spacex.util.*
import kotlinx.android.parcel.Parcelize
import java.util.*

/**
 * [Launch] data class is used by Retrofit. It holds variables needed to map API responses.
 *
 */
@Parcelize
data class Launch(
    @SerializedName(SN_FLIGHT_NUMBER) val flightNumber: Int,
    @SerializedName(LAUNCH_SUCCESS) val launchSuccess: Boolean,
    @SerializedName(SN_MISSION_NAME) val missionName: String,
    @SerializedName(SN_MISSION_DETAILS) val missionDetails: String,
    @SerializedName(SN_ROCKET) val rocket: Rocket,
    @SerializedName(SN_LAUNCH_SITE) val launchSite: LaunchSite,
    @SerializedName(LAUNCH_DATE) val launchDate: Date,
    @SerializedName(SN_LINKS) val launchLink: LaunchLink
) : Parcelable