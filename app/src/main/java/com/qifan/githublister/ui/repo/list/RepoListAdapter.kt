package com.qifan.githublister.ui.repo.list

import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.qifan.githublister.R
import com.qifan.githublister.core.extension.inflateLayout
import com.qifan.githublister.core.extension.loadFromUrl
import com.qifan.githublister.model.RepositoryModel
import io.reactivex.processors.PublishProcessor

/**
 * Created by Qifan on 2019-08-11.
 */
private const val VIEW_TYPE_ITEM = 1
private const val VIEW_TYPE_EMPTY = 2

class RepoListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val repositories: MutableList<RepositoryModel> = mutableListOf()
    private val mOnItemSelected: PublishProcessor<Int> = PublishProcessor.create()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_EMPTY -> createEmptyViewHolder(parent)
            else -> createItemViewHolder(parent)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is RepoItemViewHolder -> bindRepoItemViewHolder(holder, repositories[position])
        }
    }

    fun setData(data: List<RepositoryModel>) {
        repositories.clear()
        repositories.addAll(data)
        notifyDataSetChanged()
    }

    override fun getItemCount() = when (repositories.size) {
        0 -> 1
        else -> repositories.size
    }

    override fun getItemViewType(position: Int): Int {
        return when (repositories.size) {
            0 -> VIEW_TYPE_EMPTY
            else -> VIEW_TYPE_ITEM
        }
    }

    private fun createItemViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val view = parent.context.inflateLayout(R.layout.repository_adapter_item_layout, parent)
        return RepoItemViewHolder(view)
    }

    private fun createEmptyViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val view = parent.context.inflateLayout(R.layout.repository_adapter_no_item_layout, parent)
        return EmptyViewHolder(view)
    }

    private fun bindRepoItemViewHolder(holder: RepoItemViewHolder, repositoryModel: RepositoryModel) {
        holder.title.text = repositoryModel.name
        holder.avatar.loadFromUrl(repositoryModel.owner.avatar_url)
        holder.author.text = repositoryModel.owner.login
        repositoryModel.description?.run {
            holder.description.text = this
        } ?: holder.apply {
            description.visibility = View.GONE
        }

        holder.itemView.setOnClickListener {
            mOnItemSelected.onNext(repositoryModel.id)
        }
    }


    inner class RepoItemViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal val avatar: AppCompatImageView = itemView.findViewById(R.id.avatar)
        internal val title: AppCompatTextView = itemView.findViewById(R.id.title)
        internal val description: AppCompatTextView = itemView.findViewById(R.id.description)
        internal val author: AppCompatTextView = itemView.findViewById(R.id.detail_author)
    }

    inner class EmptyViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView)
}