package com.qifan.githublister.repository.pull

import com.qifan.githublister.core.extension.reactive.io
import com.qifan.githublister.datasource.pull.PullRemoteDataSource
import com.qifan.githublister.model.PullModel
import com.qifan.githublister.repository.RepositoryImpl
import io.reactivex.Single

/**
 * Created by Qifan on 2019-08-14.
 */
class PullRepository(private val remote: PullRemoteDataSource) : RepositoryImpl<PullRemoteDataSource>(remote) {
    fun getPulls(owner: String, repo: String): Single<List<PullModel>> {
        return remote.getPulls(owner, repo)
            .io()
    }
}