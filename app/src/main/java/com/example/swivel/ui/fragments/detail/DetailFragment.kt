package com.example.swivel.ui.fragments.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import butterknife.BindView
import butterknife.ButterKnife
import com.example.swivel.R
import com.example.swivel.dao.Article
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.squareup.picasso.Picasso

class DetailFragment : BottomSheetDialogFragment() {

    companion object {
        fun newInstance(article: Article): DetailFragment {
            val fragment = DetailFragment()
            val bundle = Bundle()
            bundle.putParcelable(ARG_ARTICLE, article)
            fragment.arguments = bundle
            return fragment
        }

        private const val ARG_ARTICLE = "ARTICLE"
    }

    @BindView(R.id.txt_title)
    lateinit var txtTitle: AppCompatTextView

    @BindView(R.id.txt_content)
    lateinit var txtContent: AppCompatTextView

    @BindView(R.id.img_resource)
    lateinit var image: AppCompatImageView

    @BindView(R.id.btn_visit_link)
    lateinit var btnVisitLink: FloatingActionButton

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val root = inflater.inflate(R.layout.fragment_detail, container, false)
        ButterKnife.bind(this, root)
        val article = arguments!!.get(ARG_ARTICLE) as Article
        article.urlToImage?.let {
            if (it.trim().isNotEmpty()) {
                Picasso.get().load(it).into(image)
            }
        }
        txtTitle.text = article.title
        txtContent.text = article.content
        btnVisitLink.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(article.url))
            startActivity(browserIntent)
        }
        return root
    }
}