package com.qifan.githublister.viewmodel

import com.nhaarman.mockito_kotlin.whenever
import com.qifan.githublister.factory.RepoFactory
import com.qifan.githublister.helper.ImmediateSchedulerRule
import com.qifan.githublister.model.detail.ContributorModel
import com.qifan.githublister.repository.repo.RepoListRepository
import com.qifan.githublister.ui.feature.repo.detail.info.CONTRIBUTOR
import com.qifan.githublister.ui.feature.repo.detail.info.contributor.ContributorViewModel
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
class ContributorViewModelTest {
    @Mock
    private lateinit var repository: RepoListRepository

    @Mock
    private lateinit var viewModel: ContributorViewModel

    @get:Rule
    val testScheduler = ImmediateSchedulerRule.instance

    @Before
    fun before() {
        MockitoAnnotations.initMocks(this)
        viewModel = ContributorViewModel(repository, CONTRIBUTOR)
    }


    @Test
    fun getContributorsSuccess() {
        val response = RepoFactory.makeContributors()
        stubGetContributors(Single.just(response))
        viewModel.getDataList("", "").test()
            .assertValue(response)
    }


    private fun stubGetContributors(single: Single<List<ContributorModel>>) {
        whenever(repository.getContributorDetail("", ""))
            .thenReturn(single)
    }
}