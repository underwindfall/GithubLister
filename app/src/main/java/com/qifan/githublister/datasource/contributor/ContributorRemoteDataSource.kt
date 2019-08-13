package com.qifan.githublister.datasource.contributor

import com.qifan.githublister.datasource.IRemoteDataSource
import com.qifan.githublister.model.ContributorModel
import com.qifan.githublister.network.ContributorService
import io.reactivex.Single

/**
 * Created by Qifan on 2019-08-13.
 */
class ContributorRemoteDataSource(private val contributorService: ContributorService) : IRemoteDataSource {

    fun getContributorDetail(owner: String, repo: String): Single<List<ContributorModel>> {
        return contributorService.getContributors(owner, repo)
    }
}