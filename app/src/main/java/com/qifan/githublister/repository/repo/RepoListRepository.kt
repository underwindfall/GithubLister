package com.qifan.githublister.repository.repo

import com.qifan.githublister.core.extension.reactive.io
import com.qifan.githublister.datasource.repo.RepoRemoteDataSource
import com.qifan.githublister.model.RepoInfoModel
import com.qifan.githublister.model.RepoModel
import com.qifan.githublister.repository.RepositoryImpl
import io.reactivex.Single

/**
 * Created by Qifan on 2019-08-11.
 */
class RepoListRepository(private val remote: RepoRemoteDataSource) :
    RepositoryImpl<RepoRemoteDataSource>(remote) {

    fun getPublicRepoList(startIndex: Int): Single<List<RepoModel>> {
        return remote.getPublicRepos(startIndex)
            .io()
    }

    fun getRepoInfo(owner: String, repo: String): Single<RepoInfoModel> {
        return remote.getRepoInfo(owner, repo)
            .io()
    }
}