package com.qifan.githublister.core.di.modules

import com.qifan.githublister.repository.repo.list.RepoListRepository
import org.koin.dsl.module

/**
 * Created by Qifan on 2019-08-11.
 */
val repositoryModules = module {
    single { RepoListRepository(get()) }
}