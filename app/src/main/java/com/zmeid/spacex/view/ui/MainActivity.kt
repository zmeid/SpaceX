package com.zmeid.spacex.view.ui

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.zmeid.spacex.R
import com.zmeid.spacex.databinding.ActivityMainBinding
import com.zmeid.spacex.model.Launch
import com.zmeid.spacex.util.ApiResponseWrapper
import com.zmeid.spacex.util.ErrorMessageGenerator
import com.zmeid.spacex.view.adapter.LaunchAdapter
import com.zmeid.spacex.view.adapter.OnItemClickListener
import com.zmeid.spacex.viewmodel.MainActivityViewModel
import timber.log.Timber
import javax.inject.Inject

/**
 * [MainActivity] is the only activity of the application.
 *
 * View binding is enabled. Communicates with [MainActivityViewModel].
 */
class MainActivity : BaseActivity(), View.OnClickListener,
    SwipeRefreshLayout.OnRefreshListener {

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProvider.Factory

    @Inject
    lateinit var layoutManager: LinearLayoutManager

    @Inject
    lateinit var launchAdapter: LaunchAdapter

    @Inject
    lateinit var errorMessageGenerator: ErrorMessageGenerator

    private lateinit var binding: ActivityMainBinding
    private lateinit var mainActivityViewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.recyclerviewLaunch.layoutManager = layoutManager
        binding.recyclerviewLaunch.adapter = launchAdapter
        setAdapterItemClickListener()

        binding.buttonRetry.setOnClickListener(this)

        binding.swipeRefreshLayout.setOnRefreshListener(this)

        mainActivityViewModel =
            ViewModelProvider(this, viewModelProviderFactory).get(MainActivityViewModel::class.java)
        mainActivityViewModel.getLaunches(false)
        observeLaunchApiResponse()
    }

    /**
     * Observes if there are any changes in launchApiResponse live data and calls [handleDefinitionResult]
     */
    private fun observeLaunchApiResponse() {
        mainActivityViewModel.launchApiResponse.observe(this, Observer {
            Timber.d("LAUNCH API RESPONSE: \n $it")
            handleDefinitionResult(it)
        })
    }

    /**
     * Handles definition results according to [ApiResponseWrapper]'s status.
     *
     * If [ApiResponseWrapper.Status.LOADING], it shows progress bar, hides user messages and hides retry button.
     * If [ApiResponseWrapper.Status.SUCCESS], updates [LaunchAdapter] with received data, hides progress bar, hides user messages and hides retry button. If the received data is empty; shows a message to user.
     * If [ApiResponseWrapper.Status.ERROR], it shows the error message and retry button. Clears recyclerview.
     */
    private fun handleDefinitionResult(apiResponseWrapper: ApiResponseWrapper<List<Launch>>) {
        when (apiResponseWrapper.status) {
            ApiResponseWrapper.Status.LOADING -> {
                showProgressBar(binding.progressBarMainActivity)
                hideUserMessageText()
                hideRetryButton()
            }
            ApiResponseWrapper.Status.SUCCESS -> {
                hideProgressBar(binding.progressBarMainActivity)
                hideUserMessageText()
                hideRetryButton()
                val launchList = apiResponseWrapper.data!!
                launchAdapter.submitList(launchList)
                if (launchList.isEmpty()) showUserMessage(getString(R.string.api_response_empty))
            }
            ApiResponseWrapper.Status.ERROR -> {
                hideProgressBar(binding.progressBarMainActivity)
                val errorMessage =
                    errorMessageGenerator.generateErrorMessage(apiResponseWrapper.exception!!)
                launchAdapter.submitList(null)
                showUserMessage(errorMessage)
                showRetryButton()
            }
        }
    }

    /**
     * Shows a message to user in the center of activity.
     */
    private fun showUserMessage(message: String) {
        binding.textViewUserMessage.apply {
            text = message
            visibility = View.VISIBLE
        }
    }

    private fun hideUserMessageText() {
        binding.textViewUserMessage.visibility = View.GONE
    }

    private fun showRetryButton() {
        binding.buttonRetry.visibility = View.VISIBLE
    }

    private fun hideRetryButton() {
        binding.buttonRetry.visibility = View.GONE
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            binding.buttonRetry.id -> {
                mainActivityViewModel.getLaunches(true)
            }
        }
    }

    /**
     * Catches swap-to-refresh event.
     */
    override fun onRefresh() {
        mainActivityViewModel.getLaunches(true)
        binding.swipeRefreshLayout.isRefreshing = false
    }

    /**
     * Catches clicks on share and play sound buttons.
     */
    private fun setAdapterItemClickListener() {
        launchAdapter.setOnItemClickedListener(object : OnItemClickListener {
            override fun onLaunchClicked(launch: Launch) {
                Timber.d("LAUNCH API RESPONSE: \n $launch")
            }
        })
    }
}