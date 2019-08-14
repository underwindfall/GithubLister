package com.qifan.githublister.ui.feature.repo.detail.branch

import com.qifan.githublister.core.base.BaseViewModel
import com.qifan.githublister.core.extension.reactive.ReactiveLoadingState
import com.qifan.githublister.core.extension.reactive.bindLoadingState
import com.qifan.githublister.core.extension.reactive.subscribeAndLogError
import com.qifan.githublister.model.BranchModel
import com.qifan.githublister.repository.repo.RepoListRepository
import io.reactivex.Flowable

/**
 * Created by Qifan on 2019-08-14.
 */
class BranchViewModel(
    repository: RepoListRepository,
    owner: String,
    repo: String
) : BaseViewModel() {

    val branches: ReactiveLoadingState<List<BranchModel>> = ReactiveLoadingState()

    private val onBranches: Flowable<List<BranchModel>> = repository.getBranches(owner, repo)
        .bindLoadingState(branches)
        .toFlowable()
        .share()

    init {
        onBranches
            .subscribeAndLogError()
            .let(compositeDisposable::add)
    }

}