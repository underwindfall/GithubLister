package com.qifan.githublister.ui.feature.repo.detail.info.branch

import com.qifan.githublister.model.detail.BranchModel
import com.qifan.githublister.repository.repo.RepoListRepository
import com.qifan.githublister.ui.feature.repo.detail.info.RepoInfoViewModel
import com.qifan.githublister.ui.feature.repo.detail.info.RepoType

/**
 * Created by Qifan on 2019-08-14.
 */
class BranchViewModel(
    repository: RepoListRepository,
    type: RepoType
) : RepoInfoViewModel<BranchModel>(repository, type)