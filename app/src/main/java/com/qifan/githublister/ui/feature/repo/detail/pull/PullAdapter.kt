package com.qifan.githublister.ui.feature.repo.detail.pull

import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.qifan.githublister.R
import com.qifan.githublister.core.extension.inflateLayout
import com.qifan.githublister.core.extension.loadAvatar
import com.qifan.githublister.model.PullModel

/**
 * Created by Qifan on 2019-08-14.
 */
private const val VIEW_TYPE_ITEM = 1
private const val VIEW_TYPE_EMPTY = 2

class PullAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val pulls: MutableList<PullModel> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_EMPTY -> createEmptyViewHolder(parent)
            else -> createItemViewHolder(parent)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is PullItemViewHolder -> bindItemViewHolder(holder, pulls[position])
        }
    }

    private fun bindItemViewHolder(
        holder: PullItemViewHolder,
        pullModel: PullModel
    ) {
        holder.title.text = pullModel.title
        holder.description.visibility = View.GONE
        holder.avatar.loadAvatar(pullModel.user.avatar_url)
        holder.author.text = pullModel.user.login
    }

    private fun createItemViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val view = parent.context.inflateLayout(R.layout.repository_adapter_item_layout, parent)
        return PullItemViewHolder(view)
    }

    private fun createEmptyViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val view = parent.context.inflateLayout(R.layout.repository_adapter_no_item_layout, parent)
        return EmptyViewHolder(view)
    }


    fun setData(data: List<PullModel>) {
        pulls.clear()
        pulls.addAll(data)
        notifyDataSetChanged()
    }

    override fun getItemCount() = when (pulls.size) {
        0 -> 1
        else -> pulls.size
    }

    override fun getItemViewType(position: Int): Int {
        return when (pulls.size) {
            0 -> VIEW_TYPE_EMPTY
            else -> VIEW_TYPE_ITEM
        }
    }

    inner class PullItemViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal val avatar: AppCompatImageView = itemView.findViewById(R.id.avatar)
        internal val title: AppCompatTextView = itemView.findViewById(R.id.title)
        internal val description: AppCompatTextView = itemView.findViewById(R.id.description)
        internal val author: AppCompatTextView = itemView.findViewById(R.id.detail_author)
    }

    inner class EmptyViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView)
}