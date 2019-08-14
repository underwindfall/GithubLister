package com.qifan.githublister.ui.feature.repo.list

import com.qifan.githublister.core.base.BaseViewModel
import com.qifan.githublister.core.extension.reactive.ReactiveLoadingState
import com.qifan.githublister.core.extension.reactive.bindLoadingState
import com.qifan.githublister.core.extension.reactive.subscribeAndLogError
import com.qifan.githublister.model.RepoModel
import com.qifan.githublister.repository.repo.RepoListRepository
import io.reactivex.Flowable
import io.reactivex.processors.PublishProcessor

/**
 * Created by Qifan on 2019-08-11.
 */
private const val START_INDEX = 0

class RepoListViewModel(private val repoListRepository: RepoListRepository) : BaseViewModel() {
    private val onFetch: PublishProcessor<Int> = PublishProcessor.create()

    val repos: ReactiveLoadingState<List<RepoModel>> = ReactiveLoadingState()

    private val onRepoList: Flowable<List<RepoModel>> = onFetch
        .switchMapSingle { index ->
            repoListRepository.getPublicRepoList(index)
                .bindLoadingState(repos)
                .onErrorReturn { emptyList() }
        }
        .share()


    fun fetchPublicRepoList(startIndex: Int) = onFetch.onNext(startIndex)

    init {
        onRepoList
            .subscribeAndLogError()
            .let(compositeDisposable::add)

        fetchPublicRepoList(START_INDEX)
    }

}