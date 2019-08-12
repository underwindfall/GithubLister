package com.qifan.githublister.core.behavior

import androidx.lifecycle.LifecycleObserver

/**
 * Created by Qifan on 2019-08-11.
 */
interface Behavior
typealias BehaviorObserver = LifecycleObserver
typealias BehaviorObservers = List<BehaviorObserver>