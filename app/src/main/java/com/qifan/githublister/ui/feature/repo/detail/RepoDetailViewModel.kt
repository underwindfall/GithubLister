package com.qifan.githublister.ui.feature.repo.detail

import com.qifan.githublister.core.base.BaseViewModel
import com.qifan.githublister.core.extension.reactive.ReactiveLoadingState
import com.qifan.githublister.core.extension.reactive.bindLoadingState
import com.qifan.githublister.core.extension.reactive.subscribeAndLogError
import com.qifan.githublister.model.RepoInfoModel
import com.qifan.githublister.repository.repo.list.RepoListRepository
import io.reactivex.Flowable

/**
 * Created by Qifan on 2019-08-13.
 */
class RepoDetailViewModel(
    repoListRepository: RepoListRepository,
    owner: String,
    repo: String
) : BaseViewModel() {
    val repoInfo: ReactiveLoadingState<RepoInfoModel> = ReactiveLoadingState()

    private val onRepoInfo: Flowable<RepoInfoModel> = repoListRepository.getRepoInfo(owner, repo)
        .bindLoadingState(repoInfo)
        .toFlowable()
        .share()

    init {
        onRepoInfo
            .subscribeAndLogError()
            .let(compositeDisposable::add)
    }
}