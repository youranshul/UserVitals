package com.android.uservitals.data

import com.android.uservitals.domain.AllVitals
import com.android.uservitals.domain.DataMapper
import com.android.uservitals.domain.Vitals
import javax.inject.Inject

class AllVitalsResponseMapper @Inject constructor() : DataMapper<UserVitals, AllVitals> {

    companion object {
        const val NO_USER_FOUND = "No user found"
    }

    override fun transform(data: UserVitals): AllVitals {
        val name = data.name ?: kotlin.run {
            NO_USER_FOUND
        }

        val dob = data.dob ?: kotlin.run {
            ""
        }
        val city = data.city ?: kotlin.run {
            ""
        }

        val listOfVitals = arrayListOf<Vitals>()
        data.vitals?.let {

            it.forEach { vital ->
                val type = vital.type
                val unit = vital.unit
                val max = vital.values?.max()
                var date: String
                var vitalModel = Vitals(type, unit, "", "")
                max?.let {
                    val idx = vital.values.indexOf(max)
                    date = vital.dates?.get(idx).toString()
                    vitalModel = Vitals(type, unit, date, max)
                }
                listOfVitals.add(vitalModel)
            }
        }
        return AllVitals(name, dob, city, listOfVitals)
    }
}