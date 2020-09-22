package com.android.uservitals.coreui.di

import com.android.uservitals.coreui.UserVitalApp
import com.android.uservitals.coreui.di.viewmodel.ActivityBindingModule
import com.android.uservitals.coreui.di.viewmodel.ViewModelModule
import com.test.nymovie.di.AppModule
import com.test.nymovie.di.RetrofitModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ViewModelModule::class,
        ActivityBindingModule::class,
        AppModule::class,
        RetrofitModule::class
    ]
)
interface AppComponent : AndroidInjector<UserVitalApp> {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance app: UserVitalApp): AppComponent
    }
}