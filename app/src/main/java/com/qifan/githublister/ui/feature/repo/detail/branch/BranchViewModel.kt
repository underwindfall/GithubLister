package com.qifan.githublister.ui.feature.repo.detail.branch

import com.qifan.githublister.core.base.BaseViewModel
import com.qifan.githublister.core.extension.reactive.ReactiveLoadingState
import com.qifan.githublister.core.extension.reactive.bindLoadingState
import com.qifan.githublister.core.extension.reactive.subscribeAndLogError
import com.qifan.githublister.model.BranchModel
import com.qifan.githublister.repository.repo.RepoListRepository

/**
 * Created by Qifan on 2019-08-14.
 */
class BranchViewModel(
    private val repository: RepoListRepository
) : BaseViewModel() {

    val branches: ReactiveLoadingState<List<BranchModel>> = ReactiveLoadingState()

    fun getBranches(owner: String, repo: String) = repository.getBranches(owner, repo)
        .bindLoadingState(branches)
        .toFlowable()
        .share()
        .subscribeAndLogError()
        .let(compositeDisposable::add)

}