package com.qifan.githublister.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Qifan on 2019-08-15.
 */
data class SearchModel(
    @SerializedName("items")
    val items: List<RepoInfoModel>
)