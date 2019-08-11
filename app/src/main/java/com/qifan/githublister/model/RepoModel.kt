package com.qifan.githublister.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Qifan on 2019-08-11.
 */
data class RepoModel(
    @SerializedName("id") val id: Int?,
    @SerializedName("name") val name: String?,
    @SerializedName("full_name") val full_name: String?,
    @SerializedName("owner") val owner: OwnerModel,
    @SerializedName("description") val description: String?,
    @SerializedName("fork") val fork: Boolean?
)