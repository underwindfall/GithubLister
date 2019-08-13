package com.qifan.githublister.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Qifan on 2019-08-14.
 */
data class BranchModel(
    @SerializedName("name")
    val name: String,
    @SerializedName("commit")
    val commit: CommitModel,
    @SerializedName("protected")
    val protected: Boolean
)