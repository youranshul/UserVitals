package com.android.uservitals.data.specificvital

import com.android.uservitals.data.UserVitals
import com.android.uservitals.data.VitalRemoteService
import com.android.uservitals.domain.ExtendedMapper
import com.android.uservitals.domain.specificvital.SpecificVital
import com.android.uservitals.domain.specificvital.SpecificVitalService
import javax.inject.Inject

class SpecificVitalRepository @Inject constructor(
    private val retrofit: VitalRemoteService,
    private val specificVitalMapper: ExtendedMapper<String, UserVitals, SpecificVital>
) : SpecificVitalService {
    override suspend fun fetchSpecificVital(type: String): SpecificVital {
        specificVitalMapper.setValue(type)
        val response = retrofit.fetchVitals()

        return specificVitalMapper.transform(response)
    }


}