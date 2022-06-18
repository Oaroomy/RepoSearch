package com.walnuty.reposearch.ui.viewmodel

import androidx.lifecycle.*
import androidx.paging.cachedIn
import com.walnuty.reposearch.common.SingleLiveEvent
import com.walnuty.reposearch.data.repository.GitApiRepository
import com.walnuty.reposearch.network.GitApi

class MainViewModel : ViewModel() {

    private val gitRepository = GitApiRepository(GitApi.create())

    var isEmptyKeyword = SingleLiveEvent<String>()
    var isProgress = MutableLiveData<Boolean>()

    private val keyword = MutableLiveData<String>()

    val repos = keyword.switchMap { keyword ->
        searchReposByKeyword(keyword)
            .cachedIn(viewModelScope)
            .asLiveData()
    }

    private fun searchReposByKeyword(keyword: String) = gitRepository.searchReposByKeyword(keyword)

    fun clickedSearchButton(input: String) {
        if (input.isNotEmpty()) {
            keyword.value = input
        } else {
            isEmptyKeyword.call()
        }
    }


}

