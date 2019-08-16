package com.qifan.githublister.ui.feature.repo.detail

import androidx.annotation.VisibleForTesting
import com.qifan.githublister.core.base.BaseViewModel
import com.qifan.githublister.core.extension.reactive.ReactiveLoadingState
import com.qifan.githublister.core.extension.reactive.bindLoadingState
import com.qifan.githublister.core.extension.reactive.subscribeAndLogError
import com.qifan.githublister.model.RepoInfoModel
import com.qifan.githublister.repository.repo.RepoListRepository
import io.reactivex.Flowable
import io.reactivex.processors.BehaviorProcessor

/**
 * Created by Qifan on 2019-08-13.
 */
class RepoDetailViewModel(
    repoListRepository: RepoListRepository,
    owner: String,
    repo: String
) : BaseViewModel() {
    private val onFetch: BehaviorProcessor<Pair<String, String>> = BehaviorProcessor.create()
    val repoInfo: ReactiveLoadingState<RepoInfoModel> = ReactiveLoadingState()

    @VisibleForTesting
    internal val onRepoInfo: Flowable<RepoInfoModel> = onFetch.switchMapSingle { (owner, repo) ->
        repoListRepository.getRepoInfo(owner, repo)
            .bindLoadingState(repoInfo)
    }
        .share()

    private fun fetchRepoInfo(
        owner: String,
        repo: String
    ) = onFetch.onNext(Pair(owner, repo))

    init {
        onRepoInfo
            .subscribeAndLogError()
            .let(compositeDisposable::add)

        fetchRepoInfo(owner, repo)
    }
}