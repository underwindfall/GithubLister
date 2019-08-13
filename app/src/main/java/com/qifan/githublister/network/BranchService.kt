package com.qifan.githublister.network

import com.qifan.githublister.model.BranchModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by Qifan on 2019-08-14.
 */
interface BranchService {
    @GET("repos/{owner}/{repo}/branches")
    fun getBranches(@Path("owner") owner: String, @Path("repo") repo: String): Single<List<BranchModel>>
}