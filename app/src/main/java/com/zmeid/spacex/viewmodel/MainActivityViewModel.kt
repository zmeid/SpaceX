package com.zmeid.spacex.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zmeid.spacex.model.Launch
import com.zmeid.spacex.repository.LaunchRepository
import com.zmeid.spacex.util.ApiResponseWrapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Viewmodel of [com.zmeid.spacex.view.ui.MainActivity].
 *
 * [LaunchRepository] is injected here.
 *
 */
class MainActivityViewModel @Inject constructor(private val launchRepository: LaunchRepository) :
    ViewModel() {

    private val launchApiResponseMutable: MutableLiveData<ApiResponseWrapper<List<Launch>>> =
        MutableLiveData()
    val launchApiResponse: LiveData<ApiResponseWrapper<List<Launch>>> = launchApiResponseMutable

    init {
        getLaunches(false)
    }

    /**
     * Asks repository to return launches and posts value to observers. Since it is an IO operation, it is done in [Dispatchers.IO] scope.
     */
    fun getLaunches(forceRefresh: Boolean) {
        if (forceRefresh || launchApiResponseMutable.value == null) {
            viewModelScope.launch(Dispatchers.IO) {
                launchApiResponseMutable.postValue(ApiResponseWrapper.loading())
                try {
                    launchApiResponseMutable.postValue(ApiResponseWrapper.success(launchRepository.getLaunches()))
                } catch (e: Exception) {
                    launchApiResponseMutable.postValue(ApiResponseWrapper.error(exception = e))
                }
            }
        }
    }
}