package com.walnuty.reposearch.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.walnuty.reposearch.R
import com.walnuty.reposearch.databinding.InflateRepoItemBinding
import com.walnuty.reposearch.model.GitRepo
import com.walnuty.reposearch.ui.viewmodel.MainViewModel

class RepoAdapter(private val viewModel: MainViewModel) : RecyclerView.Adapter<RepoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.inflate_repo_item, parent, false)
        return RepoViewHolder(view)
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        viewModel.repos.value?.let {
            holder.bind(it[position])
        }
    }

    override fun getItemCount(): Int {
        return viewModel.repos.value?.size?:0
    }

}

class RepoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val binding = InflateRepoItemBinding.bind(view)
    fun bind(repo: GitRepo) {
        binding.repo = repo
    }

}
