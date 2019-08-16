package com.qifan.githublister.viewmodel

import com.nhaarman.mockito_kotlin.whenever
import com.qifan.githublister.factory.RepoFactory
import com.qifan.githublister.helper.ImmediateSchedulerRule
import com.qifan.githublister.model.detail.IssueModel
import com.qifan.githublister.repository.repo.RepoListRepository
import com.qifan.githublister.ui.feature.repo.detail.info.ISSUE
import com.qifan.githublister.ui.feature.repo.detail.info.issue.IssueViewModel
import io.reactivex.Single
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

/**
 * Created by Qifan on 2019-08-16.
 */
@RunWith(MockitoJUnitRunner::class)
class IssueViewModelTest {
    @Mock
    private lateinit var repository: RepoListRepository

    @Mock
    private lateinit var viewModel: IssueViewModel

    @get:Rule
    val testScheduler = ImmediateSchedulerRule.instance

    @Before
    fun before() {
        MockitoAnnotations.initMocks(this)
        viewModel = IssueViewModel(repository, ISSUE)
    }


    @Test
    fun getContributorsSuccess() {
        val response = RepoFactory.makeIssues()
        stubGetIssues(Single.just(response))
        viewModel.getDataList("", "").test()
            .assertValue(response)
    }


    private fun stubGetIssues(single: Single<List<IssueModel>>) {
        whenever(repository.getIssues("", ""))
            .thenReturn(single)
    }
}