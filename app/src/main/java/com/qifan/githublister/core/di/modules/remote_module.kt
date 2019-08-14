package com.qifan.githublister.core.di.modules

import com.qifan.githublister.network.*
import org.koin.dsl.module
import retrofit2.Retrofit

/**
 * Created by Qifan on 2019-08-11.
 */
val remoteModules = module {
    single<RepoService> {
        get<Retrofit>().create(RepoService::class.java)
    }
    single<ContributorService> {
        get<Retrofit>().create(ContributorService::class.java)
    }
    single<BranchService> {
        get<Retrofit>().create(BranchService::class.java)
    }
    single<IssueService> {
        get<Retrofit>().create(IssueService::class.java)
    }
    single<PullsService> {
        get<Retrofit>().create(PullsService::class.java)
    }
}