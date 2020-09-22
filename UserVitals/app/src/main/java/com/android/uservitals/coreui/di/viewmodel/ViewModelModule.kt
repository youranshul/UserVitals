package com.android.uservitals.coreui.di.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.android.uservitals.coreui.di.viewmodel.ViewModelFactory
import com.android.uservitals.coreui.screens.vitals.UserVitalsFragment
import com.android.uservitals.coreui.screens.vitals.UserVitalsViewModel
import com.test.nymovie.di.FragmentScoped
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {



    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory):
        ViewModelProvider.Factory
}