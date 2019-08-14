package com.qifan.githublister.ui.feature.repo.detail.issue

import com.qifan.githublister.core.base.BaseViewModel
import com.qifan.githublister.core.extension.reactive.ReactiveLoadingState
import com.qifan.githublister.core.extension.reactive.bindLoadingState
import com.qifan.githublister.core.extension.reactive.subscribeAndLogError
import com.qifan.githublister.model.IssueModel
import com.qifan.githublister.repository.repo.RepoListRepository
import io.reactivex.Flowable

/**
 * Created by Qifan on 2019-08-14.
 */
class IssueViewModel(
    repository: RepoListRepository,
    owner: String,
    repo: String
) : BaseViewModel() {

    val issues: ReactiveLoadingState<List<IssueModel>> = ReactiveLoadingState()

    private val onIssues: Flowable<List<IssueModel>> = repository.getIssues(owner, repo)
        .bindLoadingState(issues)
        .toFlowable()
        .share()

    init {
        onIssues
            .subscribeAndLogError()
            .let(compositeDisposable::add)
    }

}