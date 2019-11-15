package com.example.swivel.ui.adapter

import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import com.example.swivel.R
import com.example.swivel.dao.Article
import com.squareup.picasso.Picasso

class ArticleViewHolder(mView: View) : RecyclerView.ViewHolder(mView) {

    lateinit var article: Article

    @BindView(R.id.img_resource)
    lateinit var image: AppCompatImageView

    @BindView(R.id.txt_title)
    lateinit var txtTitle: AppCompatTextView

    init {
        ButterKnife.bind(this, mView)
    }

    fun populate(e: Article) {
        reset()
        article = e
        txtTitle.text = article.title ?: ""
        article.urlToImage?.let {
            if (it.trim().isNotEmpty()) {
                Picasso.get().load(it).into(image)
                image.visibility = View.VISIBLE
            }
        }
    }

    private fun reset() {
        image.visibility = View.GONE
        image.setImageDrawable(null)
    }
}