package com.qifan.githublister.ui.feature.repo.detail.contributor

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.qifan.githublister.core.base.BaseAdapter
import com.qifan.githublister.core.extension.loadAvatar
import com.qifan.githublister.model.ContributorModel

/**
 * Created by Qifan on 2019-08-14.
 */

class ContributorAdapter : BaseAdapter<ContributorModel>() {
    override fun bindItemViewHolder(holder: RecyclerView.ViewHolder, model: ContributorModel) {
        (holder as BaseAdapter<*>.ItemViewHolder).apply {
            holder.title.visibility = View.GONE
            holder.description.text = "Number Contribution : ${model.contributions}"
            holder.avatar.loadAvatar(model.avatar_url)
            holder.author.text = model.login
        }
    }
}