package com.qifan.githublister.core.di.modules

import com.qifan.githublister.ui.feature.repo.detail.RepoDetailViewModel
import com.qifan.githublister.ui.feature.repo.detail.info.RepoType
import com.qifan.githublister.ui.feature.repo.detail.info.branch.BranchViewModel
import com.qifan.githublister.ui.feature.repo.detail.info.contributor.ContributorViewModel
import com.qifan.githublister.ui.feature.repo.detail.info.issue.IssueViewModel
import com.qifan.githublister.ui.feature.repo.detail.info.pull.PullViewModel
import com.qifan.githublister.ui.feature.repo.list.RepoListViewModel
import com.qifan.githublister.ui.feature.search.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by Qifan on 2019-08-11.
 */
val viewModelModules = module {
    viewModel { RepoListViewModel(get()) }
    viewModel { (owner: String, repo: String) -> RepoDetailViewModel(get(), owner, repo) }
    viewModel { (type: RepoType) -> ContributorViewModel(get(), type) }
    viewModel { (type: RepoType) -> BranchViewModel(get(), type) }
    viewModel { (type: RepoType) -> IssueViewModel(get(), type) }
    viewModel { (type: RepoType) -> PullViewModel(get(), type) }
    viewModel { (query: String) -> SearchViewModel(query, get()) }
}