package com.qifan.githublister.viewmodel

import com.nhaarman.mockito_kotlin.whenever
import com.qifan.githublister.factory.RepoFactory
import com.qifan.githublister.helper.ImmediateSchedulerRule
import com.qifan.githublister.model.detail.BranchModel
import com.qifan.githublister.repository.repo.RepoListRepository
import com.qifan.githublister.ui.feature.repo.detail.info.BRANCH
import com.qifan.githublister.ui.feature.repo.detail.info.branch.BranchViewModel
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
class BranchViewModelTest {
    @Mock
    private lateinit var repository: RepoListRepository

    @Mock
    private lateinit var viewModel: BranchViewModel

    @get:Rule
    val testScheduler = ImmediateSchedulerRule.instance

    @Before
    fun before() {
        MockitoAnnotations.initMocks(this)
        viewModel = BranchViewModel(repository, BRANCH)
    }


    @Test
    fun getBranchesSuccess() {
        val response = RepoFactory.makeBranches()
        stubGetBranches(Single.just(response))
        viewModel.getDataList("", "").test()
            .assertValue(response)
    }


    private fun stubGetBranches(single: Single<List<BranchModel>>) {
        whenever(repository.getBranches("", ""))
            .thenReturn(single)
    }
}