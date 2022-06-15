package com.walnuty.reposearch.ui.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.walnuty.reposearch.R
import com.walnuty.reposearch.databinding.ActivityMainBinding
import com.walnuty.reposearch.ui.adapter.RepoAdapter
import com.walnuty.reposearch.ui.viewmodel.MainViewModel
import kotlinx.coroutines.coroutineScope
import okhttp3.Dispatcher

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

        val repoAdapter = RepoAdapter(viewModel)
        binding.listRepo.adapter = repoAdapter
        viewModel.repos.observe(this) {
            binding.progressCircular.isVisible = false
            repoAdapter.notifyDataSetChanged()
        }

        viewModel.searchKeyword.observe(this) { keyword ->
            binding.progressCircular.isVisible = true
            viewModel.search(keyword)
        }
    }
}
