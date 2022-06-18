package com.walnuty.reposearch.data.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.walnuty.reposearch.model.GitRepo
import kotlinx.parcelize.Parcelize

@Parcelize
data class Repo(
    @SerializedName("node_id")
    val nodeId: String,
    @SerializedName("avatar_url")
    val profileUrl: String,
    @SerializedName("full_name")
    val repoName: String,
    @SerializedName("description")
    val description: String?,
    @SerializedName("owner")
    val repoOwner: RepoOwner
) : Parcelable

@Parcelize
data class RepoOwner(
    @SerializedName("node_id")
    val nodeId: String,
    @SerializedName("avatar_url")
    val profileUrl: String,
    @SerializedName("login")
    val userId: String
) : Parcelable

