package com.zmeid.spacex.model

import com.google.gson.annotations.SerializedName
import com.zmeid.spacex.util.SN_ROCKET_ID
import com.zmeid.spacex.util.SN_ROCKET_NAME
import com.zmeid.spacex.util.SN_ROCKET_TYPE

data class Rocket(
    @SerializedName(SN_ROCKET_ID) val rocketInt: String,
    @SerializedName(SN_ROCKET_NAME) val rocketName: String,
    @SerializedName(SN_ROCKET_TYPE) val rocketType: String
)