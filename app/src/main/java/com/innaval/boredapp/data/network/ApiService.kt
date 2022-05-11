package com.innaval.boredapp.data.network

import android.app.Activity
import com.innaval.boredapp.util.Constants.ENDPOINT
import retrofit2.http.GET

interface ApiService {

    @GET(ENDPOINT)
    suspend fun getActivity(): com.innaval.boredapp.domain.Activity
}