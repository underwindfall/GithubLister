package com.qifan.githublister.repository.issue

import com.qifan.githublister.core.extension.reactive.io
import com.qifan.githublister.datasource.issue.IssueRemoteDataSource
import com.qifan.githublister.model.IssueModel
import com.qifan.githublister.repository.RepositoryImpl
import io.reactivex.Single

/**
 * Created by Qifan on 2019-08-14.
 */
class IssueRepository(private val remote: IssueRemoteDataSource) : RepositoryImpl<IssueRemoteDataSource>(remote) {
    fun getIssues(owner: String, repo: String): Single<List<IssueModel>> {
        return remote.getIssues(owner, repo)
            .io()
    }
}