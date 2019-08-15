package com.qifan.githublister.repository.repo

import com.qifan.githublister.core.extension.reactive.io
import com.qifan.githublister.datasource.repo.RepoRemoteDataSource
import com.qifan.githublister.model.RepoInfoModel
import com.qifan.githublister.model.RepoModel
import com.qifan.githublister.model.SearchModel
import com.qifan.githublister.model.detail.BranchModel
import com.qifan.githublister.model.detail.ContributorModel
import com.qifan.githublister.model.detail.IssueModel
import com.qifan.githublister.model.detail.PullModel
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

    fun getBranches(owner: String, repo: String): Single<List<BranchModel>> {
        return remote.getBranches(owner, repo)
            .io()
    }

    fun getPulls(owner: String, repo: String): Single<List<PullModel>> {
        return remote.getPulls(owner, repo)
            .io()
    }

    fun getIssues(owner: String, repo: String): Single<List<IssueModel>> {
        return remote.getIssues(owner, repo)
            .io()
    }

    fun getContributorDetail(owner: String, repo: String): Single<List<ContributorModel>> {
        return remote.getContributorDetail(owner, repo)
            .io()
    }

    fun getSearchRepositories(query: String, page: Int): Single<SearchModel> {
        return remote.getSearchRepositories(query, page)
            .io()
    }
}