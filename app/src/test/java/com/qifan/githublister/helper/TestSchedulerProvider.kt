package com.qifan.githublister.helper

import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers

/**
 * Created by Qifan on 2019-08-15.
 */

fun asyncToSync() {
    RxJavaPlugins.reset()

    RxJavaPlugins.setIoSchedulerHandler {
        Schedulers.trampoline()
    }
}
