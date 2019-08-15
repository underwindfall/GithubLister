package com.qifan.githublister.ui.feature.repo.detail.branch

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.qifan.githublister.core.base.BaseAdapter
import com.qifan.githublister.model.BranchModel

/**
 * Created by Qifan on 2019-08-14.
 */

class BranchAdapter : BaseAdapter<BranchModel>() {
    override fun bindItemViewHolder(holder: RecyclerView.ViewHolder, model: BranchModel) {
        (holder as BaseAdapter<*>.ItemViewHolder).apply {
            holder.title.text = model.name
            holder.description.text = "Number Commit : ${model.commit.sha}"
            holder.avatar.visibility = View.GONE
            holder.author.visibility = View.GONE
        }
    }
}