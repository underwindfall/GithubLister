package com.qifan.githublister.datasource.issue

import com.qifan.githublister.datasource.IRemoteDataSource
import com.qifan.githublister.model.IssueModel
import com.qifan.githublister.network.IssueService
import io.reactivex.Single

/**
 * Created by Qifan on 2019-08-14.
 */
class IssueRemoteDataSource(private val issueService: IssueService) : IRemoteDataSource {
    fun getIssues(owner: String, repo: String): Single<List<IssueModel>> {
        return issueService.getIssues(owner, repo)
    }
}