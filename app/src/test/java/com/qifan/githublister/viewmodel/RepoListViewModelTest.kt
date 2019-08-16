package com.qifan.githublister.viewmodel

import com.nhaarman.mockito_kotlin.whenever
import com.qifan.githublister.factory.RepoFactory
import com.qifan.githublister.helper.ImmediateSchedulerRule
import com.qifan.githublister.model.RepoModel
import com.qifan.githublister.repository.repo.RepoListRepository
import com.qifan.githublister.ui.feature.repo.list.RepoListViewModel
import io.reactivex.Single
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

/**
 * Created by Qifan on 2019-08-15.
 */
@RunWith(MockitoJUnitRunner::class)
class RepoListViewModelTest {
    @Mock
    private lateinit var repository: RepoListRepository
    @Mock
    private lateinit var viewModel: RepoListViewModel

    @get:Rule
    val testScheduler = ImmediateSchedulerRule.instance

    @Before
    fun before() {
        MockitoAnnotations.initMocks(this)
        viewModel = RepoListViewModel(repository)
    }

    @Test
    fun getRepoListSuccess() {
        val response = RepoFactory.makeRepositories()
        stubGetRepos(Single.just(response))
        testScheduler.advanceTimeTo(1000)
        viewModel.onRepoList.test()
            .assertValue(response)
    }


    @Test
    fun getRepoListError() {
        val response = RepoFactory.makeEmptyRepository()
        stubGetRepos(Single.just(response))
        testScheduler.advanceTimeTo(1000)
        viewModel.onRepoList.test()
            .assertValue(response)
    }

    private fun stubGetRepos(single: Single<List<RepoModel>>) {
        whenever(repository.getPublicRepoList(0))
            .thenReturn(single)
    }
}