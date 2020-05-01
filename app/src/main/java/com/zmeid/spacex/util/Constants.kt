package com.zmeid.spacex.util

// region WebService
const val BASE_URL = "https://api.spacexdata.com/v3/launches/"
const val GET_PATH_LAUNCHES_ORDERED_BY_DATE_DESC = "past?sort=launch_date_utc&order=desc"
// endregion

// region Launch serialized names
const val SN_FLIGHT_NUMBER = "flight_number"
const val SN_MISSION_NAME= "mission_name"
const val SN_MISSION_DETAILS= "details"
const val LAUNCH_DATE = "launch_date_utc"
const val LAUNCH_SUCCESS = "launch_success"

const val SN_ROCKET = "rocket"
const val SN_ROCKET_ID = "rocket_id"
const val SN_ROCKET_NAME = "rocket_name"
const val SN_ROCKET_TYPE = "rocket_type"

const val SN_LAUNCH_SITE = "launch_site"
const val SN_LAUNCH_SITE_ID = "site_id"
const val SN_LAUNCH_SITE_NAME = "site_name"
const val SN_LAUNCH_SITE_NAME_LONG = "site_name_long"

const val SN_LINKS = "links"
const val SN_MISSION_PATCH = "mission_patch"
const val SN_MISSION_PATCH_SMALL = "mission_patch_small"
const val SN_FLICKR_IMAGES = "flickr_images"
// region

const val LAUNCH_OBJECT_INTENT_TAG = "launch"