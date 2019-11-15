package com.example.swivel.ui.fragments.headline

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.example.swivel.R
import com.example.swivel.dao.Article
import com.example.swivel.ui.adapter.ArticleViewHolder
import com.example.swivel.ui.adapter.RecyclerViewAdapter
import com.example.swivel.ui.fragments.detail.DetailFragment


class HeadlinesFragment : Fragment() {

    private lateinit var headlinesViewModel: HeadLinesViewModel

    private lateinit var mAdapter: RecyclerViewAdapter<Article, ArticleViewHolder>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        headlinesViewModel = ViewModelProviders.of(requireActivity()).get(HeadLinesViewModel::class.java)
        val list = inflater.inflate(R.layout.fragment_headlines, container, false) as RecyclerView
        mAdapter = object : RecyclerViewAdapter<Article, ArticleViewHolder>() {

            override fun onBindViewHolder(holder: ArticleViewHolder, e: Article) {
                holder.populate(e)
            }

            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
                return ArticleViewHolder(inflater.inflate(R.layout.layout_article_list_item, null))
            }

            override fun onClick(v: View) {
                val viewHolder = list.getChildViewHolder(v) as ArticleViewHolder
                val fragment = DetailFragment.newInstance(viewHolder.article)
                fragment.show(childFragmentManager, fragment.tag)
            }

        }

        with(list) {
            adapter = mAdapter
            addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
        }

        headlinesViewModel.articles.observe(this, Observer {
            mAdapter.replaceDataSource(it)
        })
        return list
    }

    override fun onResume() {
        super.onResume()
        val country = activity!!.getSharedPreferences(getString(R.string.shared_preference_name), Context.MODE_PRIVATE)
            .getString(getString(R.string.prf_country), getString(R.string.default_country))
        headlinesViewModel.setCountry(country!!)
    }
}