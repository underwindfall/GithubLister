package com.qifan.githublister.core.di

import com.qifan.githublister.core.di.modules.*

/**
 * Created by Qifan on 2019-08-11.
 */
val appModule = listOf(
    netWorkModules,
    remoteModules,
    schedulerModules,
    viewModelModules,
    repositoryModules,
    dataSourceModules
)