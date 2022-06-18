package com.walnuty.reposearch.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.walnuty.reposearch.R
import com.walnuty.reposearch.databinding.FooterLoadItemBinding

class LoadStateViewHolder(
    private val binding: FooterLoadItemBinding,
    retry: () -> Unit
) : RecyclerView.ViewHolder(binding.root) {
    init {
        binding.btnRetry.setOnClickListener { retry.invoke() }
    }

    fun bind(loadState: LoadState) {

        if (loadState is LoadState.Error) {
            binding.textErrorMsg.text = loadState.error.localizedMessage
        }

        binding.apply {
            progressCircularBottom.isVisible = loadState is LoadState.Loading
            btnRetry.isVisible = loadState is LoadState.Error
            textErrorMsg.isVisible = loadState is LoadState.Error
        }
    }

    companion object {
        fun create(parent: ViewGroup, retry: () -> Unit): LoadStateViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.footer_load_item, parent, false)
            val binding = FooterLoadItemBinding.bind(view)
            return LoadStateViewHolder(binding, retry)
        }
    }
}