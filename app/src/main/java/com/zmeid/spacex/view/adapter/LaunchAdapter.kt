package com.zmeid.spacex.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.zmeid.spacex.R
import com.zmeid.spacex.databinding.LaunchRowBinding
import com.zmeid.spacex.model.Launch
import javax.inject.Inject

/**
 * Used as adapter of launch recycler view. View binding is used to bind the views.
 *
 * It uses Eugene W. Myers's difference algorithm to calculate the minimal number of updates to convert one list into another.
 *
 * It has [OnItemClickListener] to provide click listener for each row.
 */
class LaunchAdapter @Inject constructor() :
    ListAdapter<Launch, LaunchAdapter.LaunchViewHolder>(LaunchListDiffCallback()) {

    private var listener: OnItemClickListener? = null

    fun setOnItemClickedListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LaunchViewHolder {
        return LaunchViewHolder(
            // Use view binding
            LaunchRowBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: LaunchViewHolder, position: Int) {
        val launch = getItem(position)
        holder.bind(launch)

        if (launch.launchLink.missionPatchSmall.isNotEmpty()) {
            Picasso.get()
                .load(launch.launchLink.missionPatchSmall) // For circle image, use small patch url to save bandwidth.
                .apply {
                    placeholder(R.drawable.ic_android)
                    fit()
                    into(holder.binding.circleImageViewLaunchPatch)
                }
        }

        holder.binding.root.setOnClickListener {
            listener?.onLaunchClicked(launch)
        }
    }

    class LaunchViewHolder(val binding: LaunchRowBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(launch: Launch) {
            binding.textViewMissionName.text = launch.missionName
            binding.textViewRocketName.text = launch.rocket.rocketName
            binding.textViewLaunchSiteName.text = launch.launchSite.siteName
            binding.textViewLaunchDate.text = launch.launchDate.toString()
        }
    }
}

interface OnItemClickListener {
    fun onLaunchClicked(launch: Launch)
}

private class LaunchListDiffCallback : DiffUtil.ItemCallback<Launch>() {
    override fun areItemsTheSame(oldItem: Launch, newItem: Launch): Boolean {
        return oldItem.flightNumber == newItem.flightNumber
    }

    override fun areContentsTheSame(oldItem: Launch, newItem: Launch): Boolean {
        return oldItem == newItem
    }
}