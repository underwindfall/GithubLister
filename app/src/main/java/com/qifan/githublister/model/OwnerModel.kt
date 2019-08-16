package com.qifan.githublister.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Qifan on 2019-08-11.
 */
data class OwnerModel(
    @SerializedName("id")
    val id: Int,
    @SerializedName("login")
    val login: String,
    @SerializedName("node_id")
    val nodeId: String,
    @SerializedName("avatar_url")
    val avatar_url: String,
    @SerializedName("gravatar_id")
    val gravatar_id: String
)