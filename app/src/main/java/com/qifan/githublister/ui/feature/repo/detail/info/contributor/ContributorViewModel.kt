package com.qifan.githublister.ui.feature.repo.detail.info.contributor

import com.qifan.githublister.model.detail.ContributorModel
import com.qifan.githublister.repository.repo.RepoListRepository
import com.qifan.githublister.ui.feature.repo.detail.info.RepoInfoViewModel
import com.qifan.githublister.ui.feature.repo.detail.info.RepoType

/**
 * Created by Qifan on 2019-08-14.
 */
class ContributorViewModel(
    repository: RepoListRepository,
    type: RepoType
) : RepoInfoViewModel<ContributorModel>(repository, type)