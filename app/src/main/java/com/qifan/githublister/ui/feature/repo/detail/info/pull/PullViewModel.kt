package com.qifan.githublister.ui.feature.repo.detail.info.pull

import com.qifan.githublister.model.detail.PullModel
import com.qifan.githublister.repository.repo.RepoListRepository
import com.qifan.githublister.ui.feature.repo.detail.info.RepoInfoViewModel
import com.qifan.githublister.ui.feature.repo.detail.info.RepoType

/**
 * Created by Qifan on 2019-08-14.
 */
class PullViewModel(
    repository: RepoListRepository,
    type: RepoType
) : RepoInfoViewModel<PullModel>(repository, type)