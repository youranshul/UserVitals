package com.android.uservitals.coreui.di.viewmodel

import com.android.uservitals.coreui.MainActivity
import com.android.uservitals.coreui.di.ActivityScoped
import com.android.uservitals.coreui.screens.vitals.VitalModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

    @ActivityScoped
    @ContributesAndroidInjector(
        modules = [VitalModule::class]
    )
    internal abstract fun mainActivity(): MainActivity
}