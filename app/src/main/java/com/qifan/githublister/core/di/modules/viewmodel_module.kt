package com.qifan.githublister.core.di.modules

import com.qifan.githublister.ui.feature.repo.detail.RepoDetailViewModel
import com.qifan.githublister.ui.feature.repo.detail.branch.BranchViewModel
import com.qifan.githublister.ui.feature.repo.detail.contributor.ContributorViewModel
import com.qifan.githublister.ui.feature.repo.detail.issue.IssueViewModel
import com.qifan.githublister.ui.feature.repo.detail.pull.PullViewModel
import com.qifan.githublister.ui.feature.repo.list.RepoListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by Qifan on 2019-08-11.
 */
val viewModelModules = module {
    viewModel { RepoListViewModel(get()) }
    viewModel { (owner: String, repo: String) -> RepoDetailViewModel(get(), owner, repo) }
    viewModel { (owner: String, repo: String) -> ContributorViewModel(get(), owner, repo) }
    viewModel { (owner: String, repo: String) -> BranchViewModel(get(), owner, repo) }
    viewModel { (owner: String, repo: String) -> IssueViewModel(get(), owner, repo) }
    viewModel { (owner: String, repo: String) -> PullViewModel(get(), owner, repo) }
}