package com.zmeid.spacex.view.ui

import android.view.View
import android.widget.ProgressBar
import com.squareup.picasso.Picasso
import com.zmeid.spacex.R
import com.zmeid.spacex.databinding.LaunchDetailsBinding
import com.zmeid.spacex.model.Launch
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

/**
 * It is a good practice to have a [BaseActivity] to hold common variables and methods of all activities.
 */
abstract class BaseActivity : DaggerAppCompatActivity() {

    fun showProgressBar(progressBar: ProgressBar) {
        progressBar.visibility = View.VISIBLE
    }

    fun hideProgressBar(progressBar: ProgressBar) {
        progressBar.visibility = View.GONE
    }

    /**
     * Binds all launch details information to the views.
     */
    fun setLaunchDetails(binding: LaunchDetailsBinding, launch: Launch) {
        binding.apply {
            launchDetailsContainer.visibility = View.VISIBLE
            textViewClickMissionToSeeDetails.visibility = View.GONE

            launchDetailsContainer.scrollTo(0, 0)

            // region Mission Details
            textViewDetailsMissionName.text = launch.missionName
            textViewDetailsMissionDetails.text = launch.missionDetails
            textViewDetailsFlightNumber.text = launch.flightNumber.toString()
            textViewDetailsLaunchDate.text = launch.launchDate.toString()
            textViewDetailsLaunchSuccess.text = launch.launchSuccess.toString()
            // endregion

            // region Rocket Details
            textViewDetailsRocketName.text = launch.rocket.rocketName
            textViewDetailsRocketId.text = launch.rocket.rocketId
            textViewDetailsRocketType.text = launch.rocket.rocketType
            // endregion

            // region Launch Site
            textViewDetailsLaunchSiteId.text = launch.launchSite.siteId
            textViewDetailsLaunchSiteName.text = launch.launchSite.siteName
            textViewDetailsLaunchSiteNameLong.text = launch.launchSite.siteNameLong
            // endregion

            // region Image load
            Picasso.get().load(launch.launchLink.missionPatch)
                .apply {
                    placeholder(R.drawable.ic_android)
                    into(imageViewPatch)
                }

            Picasso.get().load(launch.launchLink.flickrImages[0])
                .apply {
                    placeholder(R.drawable.ic_android)
                    into(imageViewFlickr1)
                }

            Picasso.get().load(launch.launchLink.flickrImages[1])
                .apply {
                    placeholder(R.drawable.ic_android)
                    into(imageViewFlickr2)
                }
            // endregion
        }
    }
}