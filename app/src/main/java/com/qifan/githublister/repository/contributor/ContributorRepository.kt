package com.qifan.githublister.repository.contributor

import com.qifan.githublister.core.extension.reactive.io
import com.qifan.githublister.datasource.contributor.ContributorRemoteDataSource
import com.qifan.githublister.model.ContributorModel
import com.qifan.githublister.repository.RepositoryImpl
import io.reactivex.Single

/**
 * Created by Qifan on 2019-08-14.
 */
class ContributorRepository(private val remote: ContributorRemoteDataSource) :
    RepositoryImpl<ContributorRemoteDataSource>(remote) {

    fun getContributorDetail(owner: String, repo: String): Single<List<ContributorModel>> {
        return remote.getContributorDetail(owner, repo)
            .io()
    }
}