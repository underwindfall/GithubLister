package com.qifan.githublister.network

import com.qifan.githublister.model.RepoModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Qifan on 2019-08-11.
 */
interface RepoService {

    @GET("repositories?")
    fun getRepositories(@Query("since") since: Int?): Single<List<RepoModel>>
}