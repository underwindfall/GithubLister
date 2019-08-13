package com.qifan.githublister.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Qifan on 2019-08-13.
 */
data class RepoInfoModel(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("full_name") val full_name: String,
    @SerializedName("owner") val owner: OwnerModel,
    @SerializedName("description") val description: String?,
    @SerializedName("fork") val fork: Boolean,
    @SerializedName("stargazers_count") val stargazersCount: Int,
    @SerializedName("subscribers_count") val watchesCount: Int,
    @SerializedName("watchers_count") val watchersCount: Int,
    @SerializedName("forks_count") val forksCount: Int,
    @SerializedName("open_issues_count") val openIssuesCount: Int
)