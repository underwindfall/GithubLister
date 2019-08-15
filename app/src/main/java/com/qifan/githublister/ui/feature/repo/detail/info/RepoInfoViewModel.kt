package com.qifan.githublister.ui.feature.repo.detail.info

import com.qifan.githublister.core.base.BaseViewModel
import com.qifan.githublister.core.extension.reactive.ReactiveLoadingState
import com.qifan.githublister.core.extension.reactive.bindLoadingState
import com.qifan.githublister.core.extension.reactive.subscribeAndLogError
import com.qifan.githublister.model.detail.DetailModel
import com.qifan.githublister.repository.repo.RepoListRepository
import io.reactivex.Single

/**
 * Created by Qifan on 2019-08-15.
 */
typealias RepoType = Int

const val PR = 0
const val ISSUE = 1
const val CONTRIBUTOR = 2
const val BRANCH = 3

open class RepoInfoViewModel<T : DetailModel>(
    private val repository: RepoListRepository,
    private val type: RepoType
) : BaseViewModel() {
    val dataList: ReactiveLoadingState<List<T>> = ReactiveLoadingState()

    private fun apiCall(owner: String, repo: String): Single<List<T>> =
        when (type) {
            PR -> repository.getPulls(owner, repo)
            ISSUE -> repository.getIssues(owner, repo)
            CONTRIBUTOR -> repository.getContributorDetail(owner, repo)
            BRANCH -> repository.getBranches(owner, repo)
            else -> repository.getPulls(owner, repo)
        } as Single<List<T>>


    fun getDataList(owner: String, repo: String) = apiCall(owner, repo)
        .bindLoadingState(dataList)
        .toFlowable()
        .share()
        .subscribeAndLogError()
        .let(compositeDisposable::add)
}