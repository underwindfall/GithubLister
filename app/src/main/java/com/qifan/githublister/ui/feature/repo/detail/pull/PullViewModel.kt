package com.qifan.githublister.ui.feature.repo.detail.pull

import com.qifan.githublister.core.base.BaseViewModel
import com.qifan.githublister.core.extension.reactive.ReactiveLoadingState
import com.qifan.githublister.core.extension.reactive.bindLoadingState
import com.qifan.githublister.core.extension.reactive.subscribeAndLogError
import com.qifan.githublister.model.PullModel
import com.qifan.githublister.repository.repo.RepoListRepository

/**
 * Created by Qifan on 2019-08-14.
 */
class PullViewModel(
    private val repository: RepoListRepository
) : BaseViewModel() {

    val pulls: ReactiveLoadingState<List<PullModel>> = ReactiveLoadingState()

    fun getPulls(owner: String, repo: String) = repository.getPulls(owner, repo)
        .bindLoadingState(pulls)
        .toFlowable()
        .share()
        .subscribeAndLogError()
        .let(compositeDisposable::add)
}