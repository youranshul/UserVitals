package com.android.uservitals.data

import com.android.uservitals.domain.AllVitals
import com.android.uservitals.domain.DataMapper
import com.android.uservitals.domain.VitalsFetchService
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