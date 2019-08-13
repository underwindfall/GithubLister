package com.qifan.githublister.network

import com.qifan.githublister.model.ContributorModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by Qifan on 2019-08-13.
 */
interface ContributorService {
    @GET("repos/{owner}/{repo}/contributors")
    fun getContributors(@Path("owner") owner: String, @Path("repo") repo: String): Single<List<ContributorModel>>
}