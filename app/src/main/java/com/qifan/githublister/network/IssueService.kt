package com.qifan.githublister.network

import com.qifan.githublister.model.IssueModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by Qifan on 2019-08-14.
 */
interface IssueService {
    @GET("repos/{owner}/{repo}/issues")
    fun getIssues(@Path("owner") owner: String, @Path("repo") repo: String): Single<List<IssueModel>>
}