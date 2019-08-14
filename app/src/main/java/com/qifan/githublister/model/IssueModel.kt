package com.qifan.githublister.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Qifan on 2019-08-14.
 */
data class IssueModel(
    @SerializedName("id")
    val id: Int,
    @SerializedName("node_id")
    val node_id: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("user")
    val user: OwnerModel
)