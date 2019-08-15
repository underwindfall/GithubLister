package com.qifan.githublister.datasource.repo

import com.qifan.githublister.datasource.IRemoteDataSource
import com.qifan.githublister.model.RepoInfoModel
import com.qifan.githublister.model.RepoModel
import com.qifan.githublister.model.SearchModel
import com.qifan.githublister.model.detail.BranchModel
import com.qifan.githublister.model.detail.ContributorModel
import com.qifan.githublister.model.detail.IssueModel
import com.qifan.githublister.model.detail.PullModel
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

    fun getBranches(owner: String, repo: String): Single<List<BranchModel>> {
        return repoService.getBranches(owner, repo)
    }

    fun getContributorDetail(owner: String, repo: String): Single<List<ContributorModel>> {
        return repoService.getContributors(owner, repo)
    }

    fun getIssues(owner: String, repo: String): Single<List<IssueModel>> {
        return repoService.getIssues(owner, repo)
    }

    fun getPulls(owner: String, repo: String): Single<List<PullModel>> {
        return repoService.getPulls(owner, repo)
    }

    fun getSearchRepositories(query: String, page: Int): Single<SearchModel> {
        return repoService.searchRepositories(query, page)
    }
}