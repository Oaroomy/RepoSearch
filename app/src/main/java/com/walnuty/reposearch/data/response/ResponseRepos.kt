package com.walnuty.reposearch.data.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import com.google.gson.annotations.SerializedName
import com.walnuty.reposearch.model.GitRepo

@Parcelize
data class ResponseRepos(
    val incomplete_results: Boolean,
    val items: List<Repo>,
    val total_count: Int
) : Parcelable

fun ResponseRepos.asLocalModel(): List<GitRepo> {
    return items.map { repo ->
        GitRepo(
            repoId = repo.nodeId,
            repoName = repo.repoName,
            repoDesc = repo.description?:"",
            userId = repo.repoOwner.nodeId,
            userName = repo.repoOwner.userId,
            userProfile = repo.repoOwner.profileUrl
        )
    }
}