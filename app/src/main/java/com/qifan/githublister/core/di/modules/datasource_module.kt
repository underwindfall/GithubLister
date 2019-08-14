package com.qifan.githublister.core.di.modules

import com.qifan.githublister.datasource.repo.RepoRemoteDataSource
import org.koin.dsl.module

/**
 * Created by Qifan on 2019-08-11.
 */
val dataSourceModules = module {
    single { RepoRemoteDataSource(get()) }
}