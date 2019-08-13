package com.qifan.githublister.datasource.branch

import com.qifan.githublister.datasource.IRemoteDataSource
import com.qifan.githublister.model.BranchModel
import com.qifan.githublister.network.BranchService
import io.reactivex.Single

/**
 * Created by Qifan on 2019-08-14.
 */
class BranchRemoteDataSource(private val branchService: BranchService) : IRemoteDataSource {

    fun getBranches(owner: String, repo: String): Single<List<BranchModel>> {
        return branchService.getBranches(owner, repo)
    }
}