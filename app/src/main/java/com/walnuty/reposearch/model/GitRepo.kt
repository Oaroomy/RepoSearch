package com.walnuty.reposearch.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GitRepo(
    val repoId: String,
    val userId: String,
    val userName:String,
    val repoName: String,
    val repoDesc: String,
    val userProfile: String
) : Parcelable
