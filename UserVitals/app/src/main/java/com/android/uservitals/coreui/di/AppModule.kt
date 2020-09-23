package com.test.nymovie.di


import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
class AppModule {

    @Provides
    fun providesIoDispatcher(): CoroutineDispatcher = Dispatchers.IO
}