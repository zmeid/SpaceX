package com.zmeid.spacex.model

import com.google.gson.annotations.SerializedName
import com.zmeid.spacex.util.SN_MISSION_PATCH
import com.zmeid.spacex.util.SN_MISSION_PATCH_SMALL

data class LaunchLink(
    @SerializedName(SN_MISSION_PATCH) val missionPatch: String,
    @SerializedName(SN_MISSION_PATCH_SMALL) val missionPatchSmall: String
)