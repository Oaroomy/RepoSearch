package com.walnuty.reposearch.ui

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.walnuty.reposearch.R

object BindingAdapters {

    @JvmStatic
    @BindingAdapter("imgUrl")
    fun ImageView.setImageUrl(url: String) {
        Glide.with(this).load(url)
            .placeholder(R.color.gray)
            .error(R.color.gray)
            .into(this)
    }

}