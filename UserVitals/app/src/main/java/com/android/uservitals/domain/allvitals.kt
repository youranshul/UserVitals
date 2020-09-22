package com.android.uservitals.domain

interface VitalsFetchService{
   suspend fun fetchVitals() : AllVitals
}

