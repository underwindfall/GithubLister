package com.qifan.githublister.ui.feature.search

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.qifan.githublister.core.base.BaseAdapter
import com.qifan.githublister.core.extension.loadAvatar
import com.qifan.githublister.model.RepoInfoModel
import io.reactivex.Flowable
import io.reactivex.processors.PublishProcessor

/**
 * Created by Qifan on 2019-08-15.
 */
class SearchAdapter : BaseAdapter<RepoInfoModel>() {
    private val mOnItemSelected: PublishProcessor<Pair<String, String>> = PublishProcessor.create()
    val onItemSelected: Flowable<Pair<String, String>> = mOnItemSelected

    override fun bindItemViewHolder(holder: RecyclerView.ViewHolder, model: RepoInfoModel) {
        (holder as BaseAdapter<*>.ItemViewHolder).apply {
            holder.title.text = model.name
            holder.avatar.loadAvatar(model.owner.avatar_url)
            holder.author.text = model.owner.login
            model.description?.run {
                holder.description.text = this
            } ?: holder.apply {
                description.visibility = View.GONE
            }

            holder.itemView.setOnClickListener {
                mOnItemSelected.onNext(Pair(model.owner.login, model.name))
            }
        }
    }
}