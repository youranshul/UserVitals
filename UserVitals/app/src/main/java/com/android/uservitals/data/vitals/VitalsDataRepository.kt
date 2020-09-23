package com.android.uservitals.data.vitals

import com.android.uservitals.data.UserVitals
import com.android.uservitals.data.VitalRemoteService
import com.android.uservitals.domain.vitals.AllVitals
import com.android.uservitals.domain.DataMapper
import com.android.uservitals.domain.vitals.VitalsFetchService
import javax.inject.Inject

class VitalsDataRepository @Inject constructor(
    private val retrofit: VitalRemoteService,
    private val vitalResponseMapper: DataMapper<UserVitals, AllVitals>
) : VitalsFetchService {

    override suspend fun fetchVitals(): AllVitals {

        val response = retrofit.fetchVitals()

        return vitalResponseMapper.transform(response)
    }


}