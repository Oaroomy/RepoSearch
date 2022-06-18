package com.walnuty.reposearch.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.walnuty.reposearch.data.repository.GitApiRepository.Companion.PAGE_SIZE
import com.walnuty.reposearch.data.response.asLocalModel
import com.walnuty.reposearch.model.GitRepo
import com.walnuty.reposearch.network.GitApi
import java.lang.Exception

class GithubPagingSource(
    private val api: GitApi,
    private val keyword: String
): PagingSource<Int, GitRepo>() {
    override fun getRefreshKey(state: PagingState<Int, GitRepo>): Int? {
        return state.anchorPosition?.let { position ->
            state.closestPageToPosition(position)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(position)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GitRepo> {
        val page = params.key ?: 1
        return try {
            val items = api.searchRepositoryByKeyword(keyword, page, params.loadSize).asLocalModel()
            LoadResult.Page(
                data = items,
                prevKey = if(page==1) null else page-1,
                nextKey = if(items.isEmpty()) null else page + (params.loadSize/PAGE_SIZE)
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

}