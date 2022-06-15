package com.walnuty.reposearch.ui.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.walnuty.reposearch.R
import com.walnuty.reposearch.databinding.ActivityMainBinding
import com.walnuty.reposearch.ui.adapter.RepoAdapter
import com.walnuty.reposearch.ui.viewmodel.MainViewModel

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
            viewModel.isPrgress.value = false
            repoAdapter.notifyDataSetChanged()
        }

        viewModel.isEmptyKeyword.observe(this) {
            Toast.makeText(this, resources.getString(R.string.message_empty_keyword), Toast.LENGTH_SHORT).show()
        }

    }
}
