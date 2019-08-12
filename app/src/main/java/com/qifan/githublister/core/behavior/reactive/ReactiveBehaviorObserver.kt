package com.qifan.githublister.core.behavior.reactive

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.OnLifecycleEvent
import com.qifan.githublister.core.behavior.BehaviorObserver
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by Qifan on 2019-08-11.
 */
class ReactiveBehaviorObserver(
    private val behavior: ReactiveBehavior
) : BehaviorObserver {
    private lateinit var compositeDisposable: CompositeDisposable

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun connectListener() {
        compositeDisposable = CompositeDisposable()
        behavior.startObserve(compositeDisposable)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun disconnectListener() {
        behavior.stopObserve()
        compositeDisposable.clear()
    }
}