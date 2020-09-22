package com.android.uservitals.data

import retrofit2.http.GET

interface VitalRemoteService {

    @GET("v3/cbeaa5c4-9fe3-4a60-abbf-ca95b70a48df")
    suspend fun fetchVitals(): UserVitals
}