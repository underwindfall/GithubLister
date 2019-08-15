package com.qifan.githublister.network

import com.qifan.githublister.model.RepoInfoModel
import com.qifan.githublister.model.RepoModel
import com.qifan.githublister.model.SearchModel
import com.qifan.githublister.model.detail.BranchModel
import com.qifan.githublister.model.detail.ContributorModel
import com.qifan.githublister.model.detail.IssueModel
import com.qifan.githublister.model.detail.PullModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by Qifan on 2019-08-11.
 */
interface RepoService {

    @GET("repositories?")
    fun getRepositories(@Query("since") since: Int?): Single<List<RepoModel>>

    @GET("repos/{owner}/{repo}")
    fun getRepoInfo(
        @Path("owner") owner: String,
        @Path("repo") repo: String
    ): Single<RepoInfoModel>

    @GET("repos/{owner}/{repo}/branches")
    fun getBranches(@Path("owner") owner: String, @Path("repo") repo: String): Single<List<BranchModel>>

    @GET("repos/{owner}/{repo}/pulls")
    fun getPulls(@Path("owner") owner: String, @Path("repo") repo: String): Single<List<PullModel>>

    @GET("repos/{owner}/{repo}/issues")
    fun getIssues(@Path("owner") owner: String, @Path("repo") repo: String): Single<List<IssueModel>>

    @GET("repos/{owner}/{repo}/contributors")
    fun getContributors(@Path("owner") owner: String, @Path("repo") repo: String): Single<List<ContributorModel>>


    @GET("search/repositories?")
    fun searchRepositories(
        @Query(
            value = "q",
            encoded = true
        ) query: String, @Query("page") page: Int
    ): Single<SearchModel>

}