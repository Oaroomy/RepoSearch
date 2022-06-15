package com.walnuty.reposearch.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GitRepo(
    val repoId: Int,
    val userId: Int,
    val userName:String,
    val repoName: String,
    val repoDesc: String,
    val userProfile: String
) : Parcelable
