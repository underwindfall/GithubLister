package com.qifan.githublister.repository.datasource.repo.list

import com.qifan.githublister.model.RepoModel
import com.qifan.githublister.network.RepoService
import com.qifan.githublister.repository.datasource.IRemoteDataSource
import io.reactivex.Single

/**
 * Created by Qifan on 2019-08-11.
 */
class RepoListRemoteDataSource(private val repoService: RepoService) : IRemoteDataSource {

    fun getPublicRepos(startIndex: Int): Single<List<RepoModel>> {
        return repoService.getRepositories(startIndex)
    }
}