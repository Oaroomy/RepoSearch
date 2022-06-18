package com.walnuty.reposearch.ui.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.walnuty.reposearch.R
import com.walnuty.reposearch.databinding.ActivityMainBinding
import com.walnuty.reposearch.ui.adapter.RepoAdapter
import com.walnuty.reposearch.ui.viewmodel.MainViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: MainViewModel
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        binding.vm = viewModel

        val repoAdapter = RepoAdapter()
        binding.listRepo.adapter = repoAdapter

        lifecycleScope.launch {
            repoAdapter.loadStateFlow.collectLatest { loadState ->
                val isListEmpty = loadState.refresh is LoadState.NotLoading && repoAdapter.itemCount == 0
                binding.textNoResult.isVisible = isListEmpty
                viewModel.isProgress.value = loadState.source.refresh is LoadState.Loading
            }
        }

        viewModel.repos.observe(this@MainActivity) { result ->
            lifecycleScope.launch {
                repoAdapter.submitData(result)
            }
        }

        viewModel.isEmptyKeyword.observe(this) {
            Toast.makeText(
                this,
                resources.getString(R.string.message_empty_keyword),
                Toast.LENGTH_SHORT
            ).show()
        }

    }
}
