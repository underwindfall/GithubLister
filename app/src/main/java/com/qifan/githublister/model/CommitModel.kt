package com.qifan.githublister.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Qifan on 2019-08-14.
 */
data class CommitModel(
    @SerializedName("sha")
    val sha: String,
    @SerializedName("url")
    val url: String
)