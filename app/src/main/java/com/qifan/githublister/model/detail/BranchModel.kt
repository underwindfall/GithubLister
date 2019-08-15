package com.qifan.githublister.model.detail

import com.google.gson.annotations.SerializedName
import com.qifan.githublister.model.CommitModel

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
) : DetailModel