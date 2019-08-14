package com.qifan.githublister.core.di.modules

import com.qifan.githublister.repository.branch.BranchRepository
import com.qifan.githublister.repository.contributor.ContributorRepository
import com.qifan.githublister.repository.issue.IssueRepository
import com.qifan.githublister.repository.pull.PullRepository
import com.qifan.githublister.repository.repo.RepoListRepository
import org.koin.dsl.module

/**
 * Created by Qifan on 2019-08-11.
 */
val repositoryModules = module {
    single { RepoListRepository(get()) }
    single { ContributorRepository(get()) }
    single { BranchRepository(get()) }
    single { IssueRepository(get()) }
    single { PullRepository(get()) }
}