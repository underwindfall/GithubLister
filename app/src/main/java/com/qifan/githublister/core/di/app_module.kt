package com.qifan.githublister.core.di

import com.qifan.githublister.core.di.modules.netWorkModules
import com.qifan.githublister.core.di.modules.remoteModules
import com.qifan.githublister.core.di.modules.schedulerModules

/**
 * Created by Qifan on 2019-08-11.
 */
val appModule = listOf(
    netWorkModules,
    remoteModules,
    schedulerModules
)