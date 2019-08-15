package com.qifan.githublister.model.detail

import com.google.gson.annotations.SerializedName
import com.qifan.githublister.model.OwnerModel

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
) : DetailModel