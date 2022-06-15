package com.walnuty.reposearch.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.walnuty.reposearch.data.repository.GitApiRepository
import com.walnuty.reposearch.model.GitRepo
import com.walnuty.reposearch.network.GitApi
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {

    private val gitRepository = GitApiRepository(GitApi.create())

    val searchKeyword = MutableLiveData("search")
    val repos = MutableLiveData<List<GitRepo>>()

    fun search(keyword: String) {
        viewModelScope.launch {
            repos.postValue(gitRepository.searchReposByKeyword(keyword))
        }
    }

}