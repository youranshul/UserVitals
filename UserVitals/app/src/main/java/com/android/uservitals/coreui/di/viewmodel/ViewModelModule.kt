package com.android.uservitals.coreui.di.viewmodel

import androidx.lifecycle.ViewModelProvider
import com.android.uservitals.coreui.di.viewmodel.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory):
        ViewModelProvider.Factory
}