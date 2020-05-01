package com.zmeid.spacex.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.zmeid.spacex.util.SN_FLICKR_IMAGES
import com.zmeid.spacex.util.SN_MISSION_PATCH
import com.zmeid.spacex.util.SN_MISSION_PATCH_SMALL
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LaunchLink(
    @SerializedName(SN_MISSION_PATCH) val missionPatch: String,
    @SerializedName(SN_MISSION_PATCH_SMALL) val missionPatchSmall: String,
    @SerializedName(SN_FLICKR_IMAGES) val flickrImages: List<String>
) : Parcelable