package com.android.uservitals.domain.specificvital

import com.android.uservitals.domain.vitals.Vitals

interface SpecificVitalService {

    suspend fun fetchSpecificVital(type: String): SpecificVital
}