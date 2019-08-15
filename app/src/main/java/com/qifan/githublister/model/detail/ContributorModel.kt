package com.qifan.githublister.model.detail

import com.google.gson.annotations.SerializedName

/**
 * Created by Qifan on 2019-08-13.
 */
data class ContributorModel(
    @SerializedName("id")
    val id: Int,
    @SerializedName("login")
    val login: String,
    @SerializedName("node_id")
    val nodeId: String,
    @SerializedName("avatar_url")
    val avatar_url: String,
    @SerializedName("contributions")
    val contributions: Int
) : DetailModel