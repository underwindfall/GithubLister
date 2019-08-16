package com.qifan.githublister

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import com.qifan.githublister.factory.RepoFactory
import com.qifan.githublister.model.RepoInfoModel
import com.qifan.githublister.model.RepoModel
import com.qifan.githublister.model.detail.BranchModel
import com.qifan.githublister.model.detail.ContributorModel
import com.qifan.githublister.model.detail.IssueModel
import com.qifan.githublister.model.detail.PullModel
import com.qifan.githublister.network.RepoService
import io.reactivex.Single
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

/**
 * Created by Qifan on 2019-08-15.
 */

@RunWith(JUnit4::class)
class RepoServiceTest {
    private val repoService = mock<RepoService>()


    @Test
    fun getRepositoriesTest() {
        val response = RepoFactory.makeRepositories()
        stubRepoGetRepositories(Single.just(response))
        val testRepositories = repoService.getRepositories(0).test()
        testRepositories.assertValue(response)
    }

    private fun stubRepoGetRepositories(
        observable: Single<List<RepoModel>>
    ) {
        whenever(repoService.getRepositories(0))
            .thenReturn(observable)
    }


    @Test
    fun getRepoInfoTest() {
        val response = RepoFactory.makeRepositoryInfo()
        stubRepoGetRepositoryInfo(Single.just(response))
        val testRepositories = repoService.getRepoInfo("", "").test()
        testRepositories.assertValue(response)
    }

    private fun stubRepoGetRepositoryInfo(
        observable: Single<RepoInfoModel>
    ) {
        whenever(repoService.getRepoInfo("", ""))
            .thenReturn(observable)
    }

    @Test
    fun getBranchesTest() {
        val response = RepoFactory.makeBranches()
        stubRepoGetBranches(Single.just(response))
        val testRepositories = repoService.getBranches("", "").test()
        testRepositories.assertValue(response)
    }

    private fun stubRepoGetBranches(
        observable: Single<List<BranchModel>>
    ) {
        whenever(repoService.getBranches("", ""))
            .thenReturn(observable)
    }

    @Test
    fun getIssuesTest() {
        val response = RepoFactory.makeIssues()
        stubRepoGetIssues(Single.just(response))
        val testRepositories = repoService.getIssues("", "").test()
        testRepositories.assertValue(response)
    }

    private fun stubRepoGetIssues(
        observable: Single<List<IssueModel>>
    ) {
        whenever(repoService.getIssues("", ""))
            .thenReturn(observable)
    }

    @Test
    fun getContributorsTest() {
        val response = RepoFactory.makeContributors()
        stubRepoGetContributors(Single.just(response))
        val testRepositories = repoService.getContributors("", "").test()
        testRepositories.assertValue(response)
    }

    private fun stubRepoGetContributors(
        observable: Single<List<ContributorModel>>
    ) {
        whenever(repoService.getContributors("", ""))
            .thenReturn(observable)
    }

    @Test
    fun getPullsTest() {
        val response = RepoFactory.makePulls()
        stubRepoGetPulls(Single.just(response))
        val testRepositories = repoService.getPulls("", "").test()
        testRepositories.assertValue(response)
    }

    private fun stubRepoGetPulls(
        observable: Single<List<PullModel>>
    ) {
        whenever(repoService.getPulls("", ""))
            .thenReturn(observable)
    }

}