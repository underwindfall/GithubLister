package com.qifan.githublister.helper

import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.TestScheduler
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement
import java.util.concurrent.TimeUnit

/**
 * Created by Qifan on 2019-08-15.
 */

class ImmediateSchedulerRule private constructor() : TestRule {

    private object Holder {
        val INSTANCE = ImmediateSchedulerRule()
    }

    companion object {
        val instance: ImmediateSchedulerRule by lazy { Holder.INSTANCE }
    }

    private val immediate = TestScheduler()

    override fun apply(base: Statement, d: Description): Statement {
        return object : Statement() {
            @Throws(Throwable::class)
            override fun evaluate() {
                RxJavaPlugins.setInitIoSchedulerHandler { immediate }
                RxJavaPlugins.setInitComputationSchedulerHandler { immediate }
                RxJavaPlugins.setInitNewThreadSchedulerHandler { immediate }
                RxJavaPlugins.setInitSingleSchedulerHandler { immediate }
                RxAndroidPlugins.setInitMainThreadSchedulerHandler { immediate }

                try {
                    base.evaluate()
                } finally {
                    RxJavaPlugins.reset()
                    RxAndroidPlugins.reset()
                }
            }
        }
    }

    fun advanceTimeBy(milliseconds: Long) {
        immediate.advanceTimeBy(milliseconds, TimeUnit.MILLISECONDS)

    }

    fun advanceTimeTo(milliseconds: Long) {
        immediate.advanceTimeTo(milliseconds, TimeUnit.MILLISECONDS)

    }
}