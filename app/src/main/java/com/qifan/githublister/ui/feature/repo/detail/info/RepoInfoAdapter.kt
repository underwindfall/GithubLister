package com.qifan.githublister.ui.feature.repo.detail.info

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.qifan.githublister.core.base.BaseAdapter
import com.qifan.githublister.core.extension.loadAvatar
import com.qifan.githublister.model.detail.*

/**
 * Created by Qifan on 2019-08-15.
 */
class RepoInfoAdapter : BaseAdapter<DetailModel>() {
    override fun bindItemViewHolder(holder: RecyclerView.ViewHolder, model: DetailModel) {
        (holder as BaseAdapter<*>.ItemViewHolder).apply {
            when (model) {
                is BranchModel -> bindBranchItemModel(this, model)
                is ContributorModel -> bindContributorItemModel(this, model)
                is IssueModel -> bindIssueItemModel(this, model)
                is PullModel -> bindPullItemModel(this, model)
            }
        }
    }


    private fun bindPullItemModel(holder: BaseAdapter<*>.ItemViewHolder, model: PullModel) {
        holder.title.text = model.title
        holder.description.visibility = View.GONE
        holder.avatar.loadAvatar(model.user.avatar_url)
        holder.author.text = model.user.login
    }

    private fun bindIssueItemModel(holder: BaseAdapter<*>.ItemViewHolder, model: IssueModel) {
        holder.title.text = model.title
        holder.description.visibility = View.GONE
        holder.avatar.loadAvatar(model.user.avatar_url)
        holder.author.text = model.user.login
    }

    private fun bindContributorItemModel(holder: BaseAdapter<*>.ItemViewHolder, model: ContributorModel) {
        holder.title.visibility = View.GONE
        holder.description.text = "Number Contribution : ${model.contributions}"
        holder.avatar.loadAvatar(model.avatar_url)
        holder.author.text = model.login
    }

    private fun bindBranchItemModel(holder: BaseAdapter<*>.ItemViewHolder, model: BranchModel) {
        holder.title.text = model.name
        holder.description.text = "Number Commit : ${model.commit.sha}"
        holder.avatar.visibility = View.GONE
        holder.author.visibility = View.GONE
    }
}