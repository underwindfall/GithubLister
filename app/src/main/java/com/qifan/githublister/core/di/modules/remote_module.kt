package com.qifan.githublister.core.di.modules

import com.qifan.githublister.network.RepoService
import org.koin.dsl.module
import retrofit2.Retrofit

/**
 * Created by Qifan on 2019-08-11.
 */
val remoteModules = module {
    single<RepoService> {
        get<Retrofit>().create(RepoService::class.java)
    }
}