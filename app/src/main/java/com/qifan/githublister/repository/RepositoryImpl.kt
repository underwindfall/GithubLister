package com.qifan.githublister.repository

import com.qifan.githublister.repository.datasource.IRemoteDataSource

/**
 * Created by Qifan on 2019-08-11.
 */
open class RepositoryImpl<T : IRemoteDataSource>(val remoteDataSource: T) : IRepository