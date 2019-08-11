package com.qifan.githublister.core.thread

import com.qifan.githublister.core.thread.executor.PostExecutionThread
import com.qifan.githublister.core.thread.executor.ThreadExecutor
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by Qifan on 2019-08-11.
 */
class UiThread : PostExecutionThread {
    override val scheduler: Scheduler = AndroidSchedulers.mainThread()
}

class JobExecutor : ThreadExecutor {
    override val scheduler: Scheduler = Schedulers.io()
}