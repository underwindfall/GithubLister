package com.qifan.githublister.viewmodel

import com.nhaarman.mockito_kotlin.whenever
import com.qifan.githublister.factory.RepoFactory
import com.qifan.githublister.helper.ImmediateSchedulerRule
import com.qifan.githublister.model.detail.PullModel
import com.qifan.githublister.repository.repo.RepoListRepository
import com.qifan.githublister.ui.feature.repo.detail.info.PR
import com.qifan.githublister.ui.feature.repo.detail.info.pull.PullViewModel
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
class PullViewModelTest {
    @Mock
    private lateinit var repository: RepoListRepository

    @Mock
    private lateinit var viewModel: PullViewModel

    @get:Rule
    val testScheduler = ImmediateSchedulerRule.instance

    @Before
    fun before() {
        MockitoAnnotations.initMocks(this)
        viewModel = PullViewModel(repository, PR)
    }


    @Test
    fun getContributorsSuccess() {
        val response = RepoFactory.makePulls()
        stubGetPulls(Single.just(response))
        viewModel.getDataList("", "").test()
            .assertValue(response)
    }


    private fun stubGetPulls(single: Single<List<PullModel>>) {
        whenever(repository.getPulls("", ""))
            .thenReturn(single)
    }
}