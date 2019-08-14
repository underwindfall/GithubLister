//package com.qifan.githublister.ui.feature.repo.detail
//
//import com.qifan.githublister.core.base.BaseViewModel
//import com.qifan.githublister.core.extension.reactive.ReactiveLoadingState
//import com.qifan.githublister.core.extension.reactive.bindLoadingState
//import com.qifan.githublister.core.extension.reactive.subscribeAndLogError
//import com.qifan.githublister.repository.repo.RepoListRepository
//import io.reactivex.Flowable
//
///**
// * Created by Qifan on 2019-08-14.
// */
//enum class DetailType {
//    TYPE_PR,
//    TYPE_ISSUE,
//    TYPE_CONTRIBUTOR,
//    TYPE_BRANCH
//}
//
//
//class DetailViewModel(
//    type: DetailType,
//    repository: RepoListRepository,
//    owner: String,
//    repo: String
//) : BaseViewModel() {
//    val data: ReactiveLoadingState<List<Any>> = ReactiveLoadingState()
//    val callback = when (type) {
//        DetailType.TYPE_PR -> repository.getPulls(owner, repo)
//        DetailType.TYPE_ISSUE -> repository.getIssues(owner, repo)
//        DetailType.TYPE_CONTRIBUTOR -> repository.getContributorDetail(owner, repo)
//        DetailType.TYPE_BRANCH -> repository.getBranches(owner, repo)
//    }
//    private val onData: Flowable<List<Any>> = callback
//        .bindLoadingState(data)
//        .toFlowable()
//        .share()
//
//
//    init {
//        onData
//            .subscribeAndLogError()
//            .let(compositeDisposable::add)
//    }
//}