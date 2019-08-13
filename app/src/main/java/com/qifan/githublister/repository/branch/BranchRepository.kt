package com.qifan.githublister.repository.branch

import com.qifan.githublister.core.extension.reactive.io
import com.qifan.githublister.datasource.branch.BranchRemoteDataSource
import com.qifan.githublister.model.BranchModel
import com.qifan.githublister.repository.RepositoryImpl
import io.reactivex.Single

/**
 * Created by Qifan on 2019-08-14.
 */
class BranchRepository(private val remote: BranchRemoteDataSource) :
    RepositoryImpl<BranchRemoteDataSource>(remote) {

    fun getBranches(owner: String, repo: String): Single<List<BranchModel>> {
        return remote.getBranches(owner, repo)
            .io()
    }
}