package com.qifan.githublister.ui.feature.search

import com.qifan.githublister.core.base.BaseViewModel
import com.qifan.githublister.core.extension.reactive.ReactiveLoadingState
import com.qifan.githublister.core.extension.reactive.bindLoadingState
import com.qifan.githublister.core.extension.reactive.subscribeAndLogError
import com.qifan.githublister.model.SearchModel
import com.qifan.githublister.repository.repo.RepoListRepository
import io.reactivex.Flowable
import io.reactivex.processors.PublishProcessor

/**
 * Created by Qifan on 2019-08-15.
 */
private const val START_INDEX = 1

class SearchViewModel(
    searchQuery: String,
    private val repository: RepoListRepository
) : BaseViewModel() {
    private val onFetch: PublishProcessor<Pair<String, Int>> = PublishProcessor.create()

    val search: ReactiveLoadingState<SearchModel> = ReactiveLoadingState()

    private val onSearch: Flowable<SearchModel> = onFetch
        .switchMapSingle { (query, index) ->
            repository.getSearchRepositories(query, index)
                .bindLoadingState(search)
                .onErrorReturn { SearchModel(emptyList()) }
        }
        .share()


    fun fetchSearchRepoList(query: String, startIndex: Int) = onFetch.onNext(Pair(query, startIndex))

    init {
        onSearch
            .subscribeAndLogError()
            .let(compositeDisposable::add)

        fetchSearchRepoList(searchQuery, START_INDEX)
    }

}