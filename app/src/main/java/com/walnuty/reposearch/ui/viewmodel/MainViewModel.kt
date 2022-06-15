package com.walnuty.reposearch.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.walnuty.reposearch.common.SingleLiveEvent
import com.walnuty.reposearch.data.repository.GitApiRepository
import com.walnuty.reposearch.model.GitRepo
import com.walnuty.reposearch.network.GitApi
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {

    private val gitRepository = GitApiRepository(GitApi.create())

    val repos = MutableLiveData<List<GitRepo>>()
    var isEmptyKeyword = SingleLiveEvent<String>()
    var isPrgress = MutableLiveData<Boolean>()

    fun search(keyword: String) {
        if (keyword.isNotEmpty()) {
            isPrgress.postValue(true)
            viewModelScope.launch {
                repos.postValue(gitRepository.searchReposByKeyword(keyword))
            }
        } else {
            isEmptyKeyword.call()
        }
    }

}