package com.qifan.githublister.ui.feature.repo.detail.info.issue

import com.qifan.githublister.model.detail.IssueModel
import com.qifan.githublister.repository.repo.RepoListRepository
import com.qifan.githublister.ui.feature.repo.detail.info.RepoInfoViewModel
import com.qifan.githublister.ui.feature.repo.detail.info.RepoType

/**
 * Created by Qifan on 2019-08-14.
 */
class IssueViewModel(
    repository: RepoListRepository,
    type: RepoType
) : RepoInfoViewModel<IssueModel>(repository, type)