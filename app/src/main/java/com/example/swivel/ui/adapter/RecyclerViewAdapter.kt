package com.example.swivel.ui.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class RecyclerViewAdapter<E, VH : RecyclerView.ViewHolder>() : RecyclerView.Adapter<VH>(), View.OnClickListener {

    private val mValues: MutableList<E> = ArrayList()

    constructor(values: List<E>) : this() {
        replaceDataSource(values)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.itemView.setOnClickListener(this)
        onBindViewHolder(holder, mValues[position])
    }

    abstract fun onBindViewHolder(holder: VH, e: E)

    override fun getItemCount(): Int = mValues.size

    fun replaceDataSource(values: List<E>) {
        mValues.clear()
        mValues.addAll(values)
        notifyDataSetChanged()
    }

    fun addItems(vararg values: E) {
        mValues.addAll(values)
        notifyDataSetChanged()
    }

}
