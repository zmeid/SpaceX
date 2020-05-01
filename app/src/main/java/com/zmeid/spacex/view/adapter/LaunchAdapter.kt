package com.zmeid.spacex.view.adapter

import android.app.Activity
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
class LaunchAdapter @Inject constructor(private val context: Activity) :
    ListAdapter<Launch, LaunchAdapter.LaunchViewHolder>(LaunchListDiffCallback()) {

    private var selectedItemFlightNumber = -1
    private var previousSelectedItemPosition: Int? = null

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
                    into(holder.binding.circleImageViewLaunchPatch)
                }
        }

        handleHighlightingAndSetClickListener(launch, holder.binding, position)
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

    /**
     * Handles highlighting logic and sets listener for each row. It highlights clicked row and removes highlight from the previous highlighted row.
     */
    private fun handleHighlightingAndSetClickListener(
        launch: Launch,
        binding: LaunchRowBinding,
        position: Int
    ) {
        binding.apply {

            if (launch.flightNumber == selectedItemFlightNumber) {
                cardViewLaunch.setCardBackgroundColor(
                    context.getColor(R.color.colorHighlightAdapter)
                )
            } else cardViewLaunch.setCardBackgroundColor(context.getColor(android.R.color.white))

            root.setOnClickListener {
                selectedItemFlightNumber = launch.flightNumber
                notifyItemChanged(position)
                previousSelectedItemPosition?.let {
                    notifyItemChanged(it)
                }
                previousSelectedItemPosition = position
                listener?.onLaunchClicked(launch)
            }
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