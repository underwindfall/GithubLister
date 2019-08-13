package com.qifan.githublister.ui.feature.repo.detail.contributor

import com.qifan.githublister.core.base.BaseViewModel
import com.qifan.githublister.core.extension.reactive.ReactiveLoadingState
import com.qifan.githublister.core.extension.reactive.bindLoadingState
import com.qifan.githublister.core.extension.reactive.subscribeAndLogError
import com.qifan.githublister.model.ContributorModel
import com.qifan.githublister.repository.contributor.ContributorRepository
import io.reactivex.Flowable

/**
 * Created by Qifan on 2019-08-14.
 */
class ContributorViewModel(
    repository: ContributorRepository,
    owner: String,
    repo: String
) : BaseViewModel() {

    val contributors: ReactiveLoadingState<List<ContributorModel>> = ReactiveLoadingState()

    private val onContributors: Flowable<List<ContributorModel>> = repository.getContributorDetail(owner, repo)
        .bindLoadingState(contributors)
        .toFlowable()
        .share()

    init {
        onContributors
            .subscribeAndLogError()
            .let(compositeDisposable::add)
    }

}