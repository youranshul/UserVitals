package com.android.uservitals.coreui.screens.vitalcharts

import androidx.lifecycle.ViewModel
import com.android.uservitals.coreui.di.viewmodel.ViewModelKey
import com.android.uservitals.data.UserVitals
import com.android.uservitals.data.specificvital.SpecificVitalDataMapper
import com.android.uservitals.data.specificvital.SpecificVitalRepository
import com.android.uservitals.domain.ExtendedMapper
import com.android.uservitals.domain.specificvital.SpecificVital
import com.android.uservitals.domain.specificvital.SpecificVitalService
import com.android.uservitals.domain.specificvital.SpecificVitalViewModel
import com.test.nymovie.di.FragmentScoped
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
internal abstract class VitalChartModule {

    @FragmentScoped
    @ContributesAndroidInjector
    internal abstract fun contributeUserVitalFragment(): SpecificVitalFragment

    @Binds
    @IntoMap
    @ViewModelKey(SpecificVitalViewModel::class)
    internal abstract fun bindSpecificVitalViewModel(viewModel: SpecificVitalViewModel): ViewModel

    @Binds
    internal abstract fun providesSpecificVitalsService(service: SpecificVitalRepository): SpecificVitalService

    @Binds
    internal abstract fun providesDataMapper(mapper: SpecificVitalDataMapper): ExtendedMapper<String, UserVitals, SpecificVital>

}