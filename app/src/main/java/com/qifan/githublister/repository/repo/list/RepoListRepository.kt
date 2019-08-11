package com.qifan.githublister.repository.repo.list

import com.qifan.githublister.core.extension.reactive.io
import com.qifan.githublister.model.RepoModel
import com.qifan.githublister.repository.RepositoryImpl
import com.qifan.githublister.repository.datasource.repo.list.RepoListRemoteDataSource
import io.reactivex.Single

/**
 * Created by Qifan on 2019-08-11.
 */
class RepoListRepository(private val remote: RepoListRemoteDataSource) :
    RepositoryImpl<RepoListRemoteDataSource>(remote) {

    fun getPublicRepoList(startIndex: Int): Single<List<RepoModel>> {
        return remote.getPublicRepos(startIndex)
            .io()
    }
}