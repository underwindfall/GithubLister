package com.qifan.githublister.ui.feature.repo.detail.issue

import com.qifan.githublister.core.base.BaseViewModel
import com.qifan.githublister.core.extension.reactive.ReactiveLoadingState
import com.qifan.githublister.core.extension.reactive.bindLoadingState
import com.qifan.githublister.core.extension.reactive.subscribeAndLogError
import com.qifan.githublister.model.IssueModel
import com.qifan.githublister.repository.repo.RepoListRepository

/**
 * Created by Qifan on 2019-08-14.
 */
class IssueViewModel(
    private val repository: RepoListRepository
) : BaseViewModel() {

    val issues: ReactiveLoadingState<List<IssueModel>> = ReactiveLoadingState()

    fun getIssues(owner: String, repo: String) = repository.getIssues(owner, repo)
        .bindLoadingState(issues)
        .toFlowable()
        .share()
        .subscribeAndLogError()
        .let(compositeDisposable::add)

}