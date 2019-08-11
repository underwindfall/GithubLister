package com.qifan.githublister.core.thread.executor

import io.reactivex.Scheduler

/**
 * Created by Qifan on 2019-08-11.
 */
interface ThreadExecutor {
    val scheduler: Scheduler
}