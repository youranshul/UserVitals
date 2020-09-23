package com.android.uservitals.domain.vitals

interface VitalsFetchService{
   suspend fun fetchVitals() : AllVitals
}

