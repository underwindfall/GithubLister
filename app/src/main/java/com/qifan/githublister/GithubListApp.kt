package com.qifan.githublister

import android.app.Application
import com.qifan.githublister.core.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

/**
 * Created by Qifan on 2019-08-11.
 */
class GithubListApp : Application() {
    override fun onCreate() {
        super.onCreate()
        // Start Koin
        startKoin {
            androidLogger()
            androidContext(this@GithubListApp)
            modules(appModule)
        }
    }
}