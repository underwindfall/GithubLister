package com.qifan.githublister.ui.feature.repo.detail.pull

import com.qifan.githublister.core.base.BaseViewModel
import com.qifan.githublister.core.extension.reactive.ReactiveLoadingState
import com.qifan.githublister.core.extension.reactive.bindLoadingState
import com.qifan.githublister.core.extension.reactive.subscribeAndLogError
import com.qifan.githublister.model.PullModel
import com.qifan.githublister.repository.repo.RepoListRepository
import io.reactivex.Flowable

/**
 * Created by Qifan on 2019-08-14.
 */
class PullViewModel(
    repository: RepoListRepository,
    owner: String,
    repo: String
) : BaseViewModel() {

    val pulls: ReactiveLoadingState<List<PullModel>> = ReactiveLoadingState()

    private val onPulls: Flowable<List<PullModel>> = repository.getPulls(owner, repo)
        .bindLoadingState(pulls)
        .toFlowable()
        .share()

    init {
        onPulls
            .subscribeAndLogError()
            .let(compositeDisposable::add)
    }

}