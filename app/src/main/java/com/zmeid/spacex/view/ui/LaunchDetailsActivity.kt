package com.zmeid.spacex.view.ui

import android.os.Bundle
import com.zmeid.spacex.databinding.LaunchDetailsBinding
import com.zmeid.spacex.model.Launch
import com.zmeid.spacex.util.LAUNCH_OBJECT_INTENT_TAG

/**
 * Shows launch details when the application is not in master/detail flow.
 */
class LaunchDetailsActivity : BaseActivity() {

    private lateinit var binding: LaunchDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = LaunchDetailsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val launch = intent.getParcelableExtra<Launch>(LAUNCH_OBJECT_INTENT_TAG)

        launch?.let {
            setLaunchDetails(binding, it)
        }
    }

    override fun onNavigateUp(): Boolean {
        finish()
        return true
    }
}
