package com.qifan.githublister.core.behavior

import androidx.lifecycle.Lifecycle

/**
 * Created by Qifan on 2019-08-11.
 */
interface BehaviorConsumer {
    val behaviors: BehaviorObservers

    fun Lifecycle.consumeBehaviors() {
        behaviors.forEach { behavior -> addObserver(behavior) }
    }

    fun Lifecycle.clearBehaviors() {
        behaviors.forEach { behavior -> removeObserver(behavior) }
    }
}