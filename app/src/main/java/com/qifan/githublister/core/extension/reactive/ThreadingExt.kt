package com.qifan.githublister.core.extension.reactive

import io.reactivex.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by Qifan on 2019-08-11.
 */
fun <T> Observable<T>.computation(): Observable<T> {
    return this@computation
        .subscribeOn(Schedulers.computation())
}

fun <T> Flowable<T>.computation(): Flowable<T> {
    return this@computation
        .subscribeOn(Schedulers.computation())
}

fun Completable.computation(): Completable {
    return this@computation
        .subscribeOn(Schedulers.computation())
}

fun <T> Single<T>.computation(): Single<T> {
    return this@computation
        .subscribeOn(Schedulers.computation())
}

fun <T> Maybe<T>.computation(): Maybe<T> {
    return this@computation
        .subscribeOn(Schedulers.computation())
}

fun <T> Observable<T>.compute(): Observable<T> {
    return this@compute
        .observeOn(Schedulers.computation())
}

fun <T> Flowable<T>.compute(): Flowable<T> {
    return this@compute
        .observeOn(Schedulers.computation())
}

fun Completable.compute(): Completable {
    return this@compute
        .observeOn(Schedulers.computation())
}

fun <T> Single<T>.compute(): Single<T> {
    return this@compute
        .observeOn(Schedulers.computation())
}

fun <T> Maybe<T>.compute(): Maybe<T> {
    return this@compute
        .observeOn(Schedulers.computation())
}

fun <T> Observable<T>.io(): Observable<T> {
    return this@io
        .subscribeOn(Schedulers.io())
}

fun <T> Flowable<T>.io(): Flowable<T> {
    return this@io
        .subscribeOn(Schedulers.io())
}

fun Completable.io(): Completable {
    return this@io
        .subscribeOn(Schedulers.io())
}

fun <T> Single<T>.io(): Single<T> {
    return this@io
        .subscribeOn(Schedulers.io())
}

fun <T> Maybe<T>.io(): Maybe<T> {
    return this@io
        .subscribeOn(Schedulers.io())
}

fun <T> Observable<T>.mainThread(): Observable<T> {
    return this@mainThread
        .observeOn(AndroidSchedulers.mainThread())
}

fun <T> Flowable<T>.mainThread(): Flowable<T> {
    return this@mainThread
        .observeOn(AndroidSchedulers.mainThread())
}

fun Completable.mainThread(): Completable {
    return this@mainThread
        .observeOn(AndroidSchedulers.mainThread())
}

fun <T> Single<T>.mainThread(): Single<T> {
    return this@mainThread
        .observeOn(AndroidSchedulers.mainThread())
}

fun <T> Maybe<T>.mainThread(): Maybe<T> {
    return this@mainThread
        .observeOn(AndroidSchedulers.mainThread())
}