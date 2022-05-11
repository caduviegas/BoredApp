package com.innaval.boredapp.data.repository

import com.innaval.boredapp.data.network.ApiService
import com.innaval.boredapp.data.network.SafeApiCall
import javax.inject.Inject

class MainRepository @Inject constructor(private val apiService: ApiService):SafeApiCall(){

    suspend fun getAnActivity() = safeApiCall {
        apiService.getActivity()
    }
}