package com.qifan.githublister.core.extension.reactive

import android.util.Log
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.disposables.Disposable

/**
 * Created by Qifan on 2019-08-11.
 */

//fun <T> Observable<T>.subscribeError(errorNotifier: ErrorNotifier): Disposable =
//    subscribe({}, { e -> errorNotifier.onReactiveError(e) })
//
//fun Completable.subscribeError(errorNotifier: ErrorNotifier): Disposable = subscribe({}, { e ->
//    logStreamError(e)
//    errorNotifier.onReactiveError(e)
//})
//
//fun <T> Flowable<T>.subscribeError(errorNotifier: ErrorNotifier): Disposable =
//    subscribe({}, { e ->
//        logStreamError(e)
//        errorNotifier.onReactiveError(e)
//    })
//
//fun <T> Single<T>.subscribeError(errorNotifier: ErrorNotifier): Disposable = subscribe({}, { e ->
//    logStreamError(e)
//    errorNotifier.onReactiveError(e)
//})

fun <T> Observable<T>.subscribeAndLogError(): Disposable = subscribe({}, ::logStreamError)

fun Completable.subscribeAndLogError(): Disposable = subscribe({}, ::logStreamError)

fun <T> Flowable<T>.subscribeAndLogError(): Disposable = subscribe({}, ::logStreamError)

fun <T> Single<T>.subscribeAndLogError(): Disposable = subscribe({}, ::logStreamError)

fun <T> Observable<T>.logError(): Observable<T> = doOnError(::logStreamError)

fun Completable.logError(): Completable = doOnError(::logStreamError)

fun <T> Flowable<T>.logError(): Flowable<T> = doOnError(::logStreamError)

fun <T> Single<T>.logError(): Single<T> = doOnError(::logStreamError)

private fun logStreamError(exception: Throwable) {
    Log.e("StreamError", exception.message, exception)
}