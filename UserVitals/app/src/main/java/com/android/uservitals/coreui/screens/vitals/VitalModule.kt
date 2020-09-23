package com.android.uservitals.coreui.screens.vitals

import androidx.lifecycle.ViewModel
import com.android.uservitals.coreui.di.viewmodel.ViewModelKey
import com.android.uservitals.data.AllVitalsResponseMapper
import com.android.uservitals.data.UserVitals
import com.android.uservitals.data.VitalsDataRepository
import com.android.uservitals.domain.AllVitals
import com.android.uservitals.domain.DataMapper
import com.android.uservitals.domain.UserVitalsViewModel
import com.android.uservitals.domain.VitalsFetchService
import com.test.nymovie.di.FragmentScoped
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
internal abstract class VitalModule {

    @FragmentScoped
    @ContributesAndroidInjector
    internal abstract fun contributeUserVitalFragment(): UserVitalsFragment

    @Binds
    @IntoMap
    @ViewModelKey(UserVitalsViewModel::class)
    internal abstract fun bindMovieReviewsListViewModel(viewModel: UserVitalsViewModel): ViewModel

    @Binds
    internal abstract fun providesFetchVitalsService(service: VitalsDataRepository): VitalsFetchService

    @Binds
    internal abstract fun providesDataMapper(mapper: AllVitalsResponseMapper): DataMapper<UserVitals, AllVitals>
}