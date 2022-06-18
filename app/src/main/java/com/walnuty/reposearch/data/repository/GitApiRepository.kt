package com.walnuty.reposearch.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.walnuty.reposearch.data.GithubPagingSource
import com.walnuty.reposearch.model.GitRepo
import com.walnuty.reposearch.network.GitApi
import kotlinx.coroutines.flow.Flow

class GitApiRepository(private val api: GitApi) {
    fun searchReposByKeyword(keyword: String): Flow<PagingData<GitRepo>> {
        return Pager(
            config = PagingConfig(PAGE_SIZE),
            pagingSourceFactory = { GithubPagingSource(api, keyword) }
        ).flow
    }

    companion object {
        const val PAGE_SIZE = 10
    }
}
