package com.walnuty.reposearch.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.walnuty.reposearch.R
import com.walnuty.reposearch.databinding.InflateRepoItemBinding
import com.walnuty.reposearch.model.GitRepo
import com.walnuty.reposearch.ui.viewmodel.MainViewModel

class RepoAdapter :
    PagingDataAdapter<GitRepo, RepoViewHolder>(REPO_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.inflate_repo_item, parent, false)
        return RepoViewHolder(view)
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        val repo = getItem(position)
        if ( repo != null) {
            holder.bind(repo)
        }
    }

    companion object {
        private val REPO_COMPARATOR = object : DiffUtil.ItemCallback<GitRepo>() {
            override fun areItemsTheSame(oldItem: GitRepo, newItem: GitRepo): Boolean {
                return oldItem.repoId == newItem.repoId
            }

            override fun areContentsTheSame(oldItem: GitRepo, newItem: GitRepo): Boolean {
                return oldItem == newItem
            }

        }
    }

}

class RepoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val binding = InflateRepoItemBinding.bind(view)
    fun bind(repo: GitRepo) {
        binding.repo = repo
    }

}
