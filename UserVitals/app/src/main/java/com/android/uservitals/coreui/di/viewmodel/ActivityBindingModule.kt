package com.android.uservitals.coreui.di.viewmodel

import com.android.uservitals.coreui.MainActivity
import com.android.uservitals.coreui.di.ActivityScoped
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

    @ActivityScoped
    @ContributesAndroidInjector(
        modules = []
    )
    internal abstract fun mainActivity(): MainActivity
}