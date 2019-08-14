package com.qifan.githublister.datasource.pull

import com.qifan.githublister.datasource.IRemoteDataSource
import com.qifan.githublister.model.PullModel
import com.qifan.githublister.network.PullsService
import io.reactivex.Single

/**
 * Created by Qifan on 2019-08-14.
 */
class PullRemoteDataSource(private val pullsService: PullsService) : IRemoteDataSource {
    fun getPulls(owner: String, repo: String): Single<List<PullModel>> {
        return pullsService.getPulls(owner, repo)
    }
}