package com.qifan.githublister.core.behavior

import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

/**
 * Created by Qifan on 2019-08-11.
 */
typealias BehaviorObserversBuilderCallback = BehaviorObserversBuilder.() -> Unit

class BehaviorObserversBuilder(val callback: BehaviorObserversBuilderCallback) {
    private val behaviorObservers: MutableList<BehaviorObserver> = mutableListOf()

    fun use(vararg behaviorObserver: BehaviorObserver): BehaviorObserversBuilder {
        behaviorObservers.apply {
            clear()
            addAll(behaviorObserver)
        }
        return this
    }

    fun build(): BehaviorObservers {
        callback(this)
        return behaviorObservers.toList()
    }
}

fun emptyBehaviors(): ReadOnlyProperty<BehaviorConsumer, BehaviorObservers> = BehaviorProvider {}

fun builder(callback: BehaviorObserversBuilderCallback): ReadOnlyProperty<BehaviorConsumer, BehaviorObservers> =
    BehaviorProvider(callback)

private class BehaviorProvider(val callback: BehaviorObserversBuilderCallback) :
    ReadOnlyProperty<BehaviorConsumer, BehaviorObservers>, BehaviorConsumer {
    override val behaviors: BehaviorObservers by lazy {
        BehaviorObserversBuilder(callback).build()
    }

    override fun getValue(thisRef: BehaviorConsumer, property: KProperty<*>): BehaviorObservers {
        return behaviors
    }
}