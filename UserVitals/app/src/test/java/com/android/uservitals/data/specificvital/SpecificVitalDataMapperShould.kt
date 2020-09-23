package com.android.uservitals.data.specificvital

import com.android.uservitals.data.AllVitalsResponseMapperShould
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class SpecificVitalDataMapperShould {

    private lateinit var mapper: SpecificVitalDataMapper

    @Before
    fun setUp() {
        mapper = SpecificVitalDataMapper()
    }

    @Test(expected = RuntimeException::class)
    fun `throw exception when type is not set`() {
        mapper.transform(AllVitalsResponseMapperShould.getResponse())
    }

    @Test
    fun `return specificVital with empty date response vital is null`() {
        mapper.setValue(AllVitalsResponseMapperShould.TYPE)
        val vital = mapper.transform(AllVitalsResponseMapperShould.getResponseForNull())
        Assert.assertTrue(vital.dates.isEmpty())
        Assert.assertTrue(vital.values.isEmpty())

    }

    @Test
    fun `map to correct value if colon exists in the response`() {
        mapper.setValue(AllVitalsResponseMapperShould.TYPE)
        val vital = mapper.transform(AllVitalsResponseMapperShould.getResponseWithColon())
        Assert.assertFalse(vital.values.first().contains(":"))

    }

    @Test
    fun `return specificVital with proper data response proper`() {
        mapper.setValue(AllVitalsResponseMapperShould.TYPE)
        val vital = mapper.transform(AllVitalsResponseMapperShould.getResponse())
        Assert.assertTrue(vital.dates.isNotEmpty())
        Assert.assertTrue(vital.values.isNotEmpty())
    }
}