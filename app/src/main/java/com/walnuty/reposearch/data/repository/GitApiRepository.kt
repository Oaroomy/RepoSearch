package com.walnuty.reposearch.data.repository

import com.walnuty.reposearch.data.response.asLocalModel
import com.walnuty.reposearch.model.GitRepo
import com.walnuty.reposearch.network.GitApi

class GitApiRepository(private val service: GitApi) {

    suspend fun searchReposByKeyword(keyword: String):List<GitRepo> {
        return service.searchRepositoryByKeyword(keyword).asLocalModel()
    }
}