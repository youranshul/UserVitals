package com.android.uservitals.coreui.screens.vitalcharts

import com.android.uservitals.coreui.screens.vitals.SpecificVitalFragment
import com.test.nymovie.di.FragmentScoped
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
internal abstract class VitalChartModule {

    @FragmentScoped
    @ContributesAndroidInjector
    internal abstract fun contributeUserVitalFragment(): SpecificVitalFragment
}