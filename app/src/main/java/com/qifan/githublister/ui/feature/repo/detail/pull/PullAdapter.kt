package com.qifan.githublister.ui.feature.repo.detail.pull

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.qifan.githublister.core.extension.loadAvatar
import com.qifan.githublister.model.PullModel
import com.qifan.githublister.ui.feature.repo.detail.DetailAdapter

/**
 * Created by Qifan on 2019-08-14.
 */

class PullAdapter : DetailAdapter<PullModel>() {
    override fun bindItemViewHolder(holder: RecyclerView.ViewHolder, model: PullModel) {
        (holder as DetailAdapter<*>.ItemViewHolder).apply {
            holder.title.text = model.title
            holder.description.visibility = View.GONE
            holder.avatar.loadAvatar(model.user.avatar_url)
            holder.author.text = model.user.login
        }
    }
}