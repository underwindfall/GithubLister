package com.qifan.githublister.ui.feature.repo.detail

import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.qifan.githublister.R
import com.qifan.githublister.core.extension.inflateLayout

/**
 * Created by Qifan on 2019-08-14.
 */
private const val VIEW_TYPE_ITEM = 1
private const val VIEW_TYPE_EMPTY = 2


abstract class DetailAdapter<T> : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val branches: MutableList<T> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_EMPTY -> createEmptyViewHolder(parent)
            else -> createItemViewHolder(parent)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when {
            branches.isNotEmpty() -> bindItemViewHolder(holder, branches[position])
        }
    }

    abstract fun bindItemViewHolder(holder: RecyclerView.ViewHolder, model: T)

    private fun createItemViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val view = parent.context.inflateLayout(R.layout.repository_adapter_item_layout, parent)
        return ItemViewHolder(view)
    }

    private fun createEmptyViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val view = parent.context.inflateLayout(R.layout.repository_adapter_no_item_layout, parent)
        return EmptyViewHolder(view)
    }


    fun setData(data: List<T>) {
        branches.clear()
        branches.addAll(data)
        notifyDataSetChanged()
    }

    override fun getItemCount() = when (branches.size) {
        0 -> 1
        else -> branches.size
    }

    override fun getItemViewType(position: Int): Int {
        return when (branches.size) {
            0 -> VIEW_TYPE_EMPTY
            else -> VIEW_TYPE_ITEM
        }
    }

    inner class ItemViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal val avatar: AppCompatImageView = itemView.findViewById(R.id.avatar)
        internal val title: AppCompatTextView = itemView.findViewById(R.id.title)
        internal val description: AppCompatTextView = itemView.findViewById(R.id.description)
        internal val author: AppCompatTextView = itemView.findViewById(R.id.detail_author)
    }

    inner class EmptyViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView)
}