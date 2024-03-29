package com.qifan.githublister.core.extension.reactive

import androidx.annotation.VisibleForTesting
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.processors.BehaviorProcessor

/**
 * Created by Qifan on 2019-08-11.
 */
class ReactiveLoadingState<T> {
    private val stateSink: BehaviorProcessor<State<T>> = BehaviorProcessor.createDefault(State(Status.INIT))

    @VisibleForTesting
    val state = stateSink
        .computation()

    val loading: Flowable<Boolean> = state
        .map { it.status == Status.LOADING }
        .distinctUntilChanged()

    val error: Flowable<Pair<Boolean, Throwable?>> = state
        .map { (it.status == Status.ERROR) to (it.error) }
        .distinctUntilChanged()

    val success: Flowable<T> = state
        .filter { it.status == Status.SUCCESS && it.data != null }
        .map { it.data!! }
        .distinctUntilChanged()

    fun onSuccess(data: T) = stateSink.onNext(State(Status.SUCCESS, data, null))
    fun onError(throwable: Throwable) = stateSink.onNext(State(Status.ERROR, null, throwable))
    fun onLoading() = stateSink.onNext(State(Status.LOADING, null, null))

    enum class Status {
        INIT,
        LOADING,
        SUCCESS,
        ERROR
    }

    data class State<T>(
        val status: Status,
        val data: T? = null,
        val error: Throwable? = null
    )
}

fun <T> Single<T>.bindLoadingState(asyncOperationState: ReactiveLoadingState<T>): Single<T> =
    doOnSubscribe { asyncOperationState.onLoading() }
        .doOnSuccess { asyncOperationState.onSuccess(it) }
        .doOnError { asyncOperationState.onError(it) }