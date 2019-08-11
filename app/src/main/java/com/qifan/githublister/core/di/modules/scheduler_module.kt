package com.qifan.githublister.core.di.modules

import com.qifan.githublister.core.thread.JobExecutor
import com.qifan.githublister.core.thread.UiThread
import com.qifan.githublister.core.thread.executor.PostExecutionThread
import com.qifan.githublister.core.thread.executor.ThreadExecutor
import org.koin.dsl.bind
import org.koin.dsl.module

/**
 * Created by Qifan on 2019-08-11.
 */
val schedulerModules = module {
    single { JobExecutor() } bind ThreadExecutor::class
    single { UiThread() } bind PostExecutionThread::class
}