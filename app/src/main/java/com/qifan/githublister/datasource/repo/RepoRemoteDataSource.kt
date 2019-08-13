package com.qifan.githublister.datasource.repo

import com.qifan.githublister.datasource.IRemoteDataSource
import com.qifan.githublister.model.RepoInfoModel
import com.qifan.githublister.model.RepoModel
import com.qifan.githublister.network.RepoService
import io.reactivex.Single

/**
 * Created by Qifan on 2019-08-11.
 */
class RepoRemoteDataSource(private val repoService: RepoService) : IRemoteDataSource {

    fun getPublicRepos(startIndex: Int): Single<List<RepoModel>> {
        return repoService.getRepositories(startIndex)
    }

    fun getRepoInfo(owner: String, repo: String): Single<RepoInfoModel> {
        return repoService.getRepoInfo(owner, repo)
    }
}