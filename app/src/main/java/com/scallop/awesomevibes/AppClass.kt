package com.scallop.awesomevibes

import android.app.Application
import com.scallop.awesomevibes.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AppClass : Application() {

    override fun onCreate() {
        super.onCreate()
        loadKoin()
    }

    private fun loadKoin() {
        startKoin {
            androidContext(this@AppClass)
            modules(
                listOf(
                    mNetworkModules,
                    mLocalModules,
                    mViewModels,
                    mRepositoryModules,
                    mUseCaseModules,
                    mOtherModules,
                )
            )
        }
    }
}