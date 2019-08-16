package com.qifan.githublister.factory

import com.qifan.githublister.helper.randomBoolean
import com.qifan.githublister.helper.randomInt
import com.qifan.githublister.helper.randomString
import com.qifan.githublister.helper.randomUuid
import com.qifan.githublister.model.*
import com.qifan.githublister.model.detail.BranchModel
import com.qifan.githublister.model.detail.ContributorModel
import com.qifan.githublister.model.detail.IssueModel
import com.qifan.githublister.model.detail.PullModel

/**
 * Created by Qifan on 2019-08-15.
 */
object RepoFactory {
    fun makeRepositories(count: Int = 10): List<RepoModel> {
        val response = mutableListOf<RepoModel>()
        repeat(count) {
            response.add(makeRepository())
        }
        return response
    }

    fun makeEmptyRepository(): List<RepoModel> = emptyList()

    fun makeRepositoryInfos(count: Int = 10): List<RepoInfoModel> {
        val response = mutableListOf<RepoInfoModel>()
        repeat(count) {
            response.add(makeRepositoryInfo())
        }
        return response
    }

    fun makeRepositoryInfo(): RepoInfoModel {
        return RepoInfoModel(
            randomInt(), randomString(), randomUuid(), makeOwner(), randomString(), randomBoolean(),
            randomInt(), randomInt(), randomInt(), randomInt(), randomInt()
        )
    }

    fun makeBranches(count: Int = 10): List<BranchModel> {
        val response = mutableListOf<BranchModel>()
        repeat(count) {
            response.add(makeBranch())
        }
        return response
    }

    fun makePulls(count: Int = 10): List<PullModel> {
        val response = mutableListOf<PullModel>()
        repeat(count) {
            response.add(makePull())
        }
        return response
    }

    private fun makePull(): PullModel {
        return PullModel(
            randomInt(), randomString(), randomUuid(), makeOwner()
        )

    }

    fun makeIssues(count: Int = 10): List<IssueModel> {
        val response = mutableListOf<IssueModel>()
        repeat(count) {
            response.add(makeIssue())
        }
        return response
    }

    private fun makeIssue(): IssueModel {
        return IssueModel(
            randomInt(), randomString(), randomUuid(), makeOwner()
        )
    }


    fun makeContributors(count: Int = 10): List<ContributorModel> {
        val response = mutableListOf<ContributorModel>()
        repeat(count) {
            response.add(makeContributor())
        }
        return response
    }

    private fun makeContributor(): ContributorModel {
        return ContributorModel(
            randomInt(), randomString(), randomUuid(), randomString(), randomInt()
        )
    }

    private fun makeBranch(): BranchModel {
        return BranchModel(randomString(), makeCommitModel(), randomBoolean())
    }

    private fun makeCommitModel(): CommitModel {
        return CommitModel(randomString(), randomString())
    }

    fun makeRepository(): RepoModel {
        return RepoModel(
            randomInt(), randomString(), randomUuid(), makeOwner(), randomString(), randomBoolean()
        )
    }

    private fun makeOwner(): OwnerModel {
        return OwnerModel(
            randomInt(), randomString(), randomUuid(), randomString(), randomString()
        )
    }

    fun makeSearchRepository(): SearchModel {
        return SearchModel(
            makeRepositoryInfos()
        )
    }
}