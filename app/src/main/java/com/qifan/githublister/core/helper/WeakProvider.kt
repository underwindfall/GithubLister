package com.qifan.githublister.core.helper

import java.lang.ref.WeakReference
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * Created by Qifan on 2019-08-11.
 */
class WeakReferenceProvider<T> : ReadWriteProperty<Any, T> {
    private var mRef: WeakReference<T>? = null

    override fun getValue(thisRef: Any, property: KProperty<*>): T {
        return mRef?.get() ?: throw IllegalStateException()
    }

    override fun setValue(thisRef: Any, property: KProperty<*>, value: T) {
        this.mRef = WeakReference(value)
    }
}