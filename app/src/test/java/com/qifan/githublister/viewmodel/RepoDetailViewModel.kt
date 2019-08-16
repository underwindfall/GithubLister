package com.qifan.githublister.viewmodel

import com.nhaarman.mockito_kotlin.whenever
import com.qifan.githublister.factory.RepoFactory
import com.qifan.githublister.helper.ImmediateSchedulerRule
import com.qifan.githublister.model.RepoInfoModel
import com.qifan.githublister.repository.repo.RepoListRepository
import com.qifan.githublister.ui.feature.repo.detail.RepoDetailViewModel
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
class RepoDetailViewModelTest {
    @Mock
    private lateinit var repository: RepoListRepository

    @Mock
    private lateinit var viewModel: RepoDetailViewModel

    @get:Rule
    val testScheduler = ImmediateSchedulerRule.instance

    @Before
    fun before() {
        MockitoAnnotations.initMocks(this)
        viewModel = RepoDetailViewModel(repository, "", "")
    }


    @Test
    fun getRepoDetailSuccess() {
        val response = RepoFactory.makeRepositoryInfo()
        stubGetRepoInfo(Single.just(response))
        viewModel.onRepoInfo.test()
            .assertValue(response)
    }


    private fun stubGetRepoInfo(single: Single<RepoInfoModel>) {
        whenever(repository.getRepoInfo("", ""))
            .thenReturn(single)
    }
}