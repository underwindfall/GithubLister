package com.qifan.githublister.ui.feature.repo.detail.contributor

import com.qifan.githublister.core.base.BaseViewModel
import com.qifan.githublister.core.extension.reactive.ReactiveLoadingState
import com.qifan.githublister.core.extension.reactive.bindLoadingState
import com.qifan.githublister.core.extension.reactive.subscribeAndLogError
import com.qifan.githublister.model.ContributorModel
import com.qifan.githublister.repository.repo.RepoListRepository

/**
 * Created by Qifan on 2019-08-14.
 */
class ContributorViewModel(
    private val repository: RepoListRepository
) : BaseViewModel() {

    val contributors: ReactiveLoadingState<List<ContributorModel>> = ReactiveLoadingState()

    fun getContributors(owner: String, repo: String) = repository.getContributorDetail(owner, repo)
        .bindLoadingState(contributors)
        .toFlowable()
        .share()
        .subscribeAndLogError()
        .let(compositeDisposable::add)

}