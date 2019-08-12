package com.qifan.githublister.core.behavior.reactive

import com.qifan.githublister.core.behavior.Behavior
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by Qifan on 2019-08-11.
 */
interface ReactiveBehavior : Behavior {
    fun startObserve(compositeDisposable: CompositeDisposable)
    fun stopObserve() {}
}

fun ReactiveBehavior.reactive() = ReactiveBehaviorObserver(
    behavior = this
)