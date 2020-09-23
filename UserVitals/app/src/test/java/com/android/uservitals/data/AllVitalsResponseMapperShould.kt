package com.android.uservitals.data

import com.android.uservitals.data.vitals.AllVitalsResponseMapper
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule

class AllVitalsResponseMapperShould {

    @Rule
    @JvmField
    val rule: MockitoRule = MockitoJUnit.rule()


    private lateinit var mapper: AllVitalsResponseMapper

    @Before
    fun setUp() {
        mapper = AllVitalsResponseMapper()
    }

    @Test
    fun `map to name,dob and city from response`() {
        val model = mapper.transform(getResponse())
        Assert.assertEquals(model.name, NAME)
        Assert.assertEquals(model.dob, AGE)
        Assert.assertEquals(model.city, CITY)
    }

    @Test
    fun `map to get max value from response`() {
        val model = mapper.transform(getResponse())
        val vitals = model.vitals.get(0)
        Assert.assertEquals(vitals.maxValue, MAX_VAL)

    }

    @Test
    fun `map to String user not found and empty other field when null in response`() {
        val model = mapper.transform(getResponseForNull())
        Assert.assertEquals(AllVitalsResponseMapper.NO_USER_FOUND, model.name)
        Assert.assertEquals(model.dob, "")
        Assert.assertEquals(model.city, "")
        Assert.assertTrue(model.vitals.isEmpty())
    }




    companion object {
        const val NAME = "testName"
        const val AGE = "testAge"
        const val CITY = "testCity"
        const val TYPE = "testType"
        const val UNIT = "testUnit"
        const val MAX_VAL = "90"
        fun getResponse(): UserVitals {
            val values = arrayListOf<String>("80", MAX_VAL)
            val dates = arrayListOf<String>("31/08/2020", "1/09/2020")
            val vitals = Vital(TYPE, UNIT, dates, values)
            return UserVitals(NAME, AGE, CITY, arrayListOf<Vital>(vitals))
        }

        fun getResponseWithColon(): UserVitals {
            val values = arrayListOf<String>("80:12", "90:50")
            val dates = arrayListOf<String>("31/08/2020", "1/09/2020")
            val vitals = Vital(TYPE, UNIT, dates, values)
            return UserVitals(NAME, AGE, CITY, arrayListOf<Vital>(vitals))
        }

        fun getResponseForNull(): UserVitals {
            return UserVitals(null, null, null, null)
        }
    }


}