package com.android.uservitals.data.specificvital

import com.android.uservitals.data.UserVitals
import com.android.uservitals.domain.ExtendedMapper
import com.android.uservitals.domain.specificvital.SpecificVital
import javax.inject.Inject

class SpecificVitalDataMapper @Inject constructor() :
    ExtendedMapper<String, UserVitals, SpecificVital> {

    private var type: String? = null

    override fun transform(data: UserVitals): SpecificVital {
        if (type == null) {
            throw RuntimeException("type is not set")
        }
        val specificVital = data.vitals?.find {
            it.type == type
        }
        return specificVital?.let {
            val values = it.values?.map { value ->
                if (value.contains(":")) {
                    return@map value.replace(":", ".")
                }
                value
            }.orEmpty()
            SpecificVital(values, it.dates.orEmpty())
        } ?: kotlin.run {
            SpecificVital(emptyList(), emptyList())
        }
    }

    override fun setValue(option: String) {
        type = option
    }
}