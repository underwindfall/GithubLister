package com.qifan.githublister.network

import com.qifan.githublister.model.PullModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by Qifan on 2019-08-14.
 */
interface PullsService {
    @GET("repos/{owner}/{repo}/pulls")
    fun getPulls(@Path("owner") owner: String, @Path("repo") repo: String): Single<List<PullModel>>
}