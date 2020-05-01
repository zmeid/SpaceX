package com.zmeid.spacex.model

import com.google.gson.annotations.SerializedName
import com.zmeid.spacex.util.SN_LAUNCH_SITE_ID
import com.zmeid.spacex.util.SN_LAUNCH_SITE_NAME
import com.zmeid.spacex.util.SN_LAUNCH_SITE_NAME_LONG

data class LaunchSite(
    @SerializedName(SN_LAUNCH_SITE_ID) val siteId: String,
    @SerializedName(SN_LAUNCH_SITE_NAME) val siteName: String,
    @SerializedName(SN_LAUNCH_SITE_NAME_LONG) val siteNameLong: String
)