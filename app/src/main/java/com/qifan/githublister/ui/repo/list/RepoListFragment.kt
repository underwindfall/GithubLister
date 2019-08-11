package com.qifan.githublister.ui.repo.list

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.qifan.githublister.R
import com.qifan.githublister.core.base.BaseFragment
import com.qifan.githublister.core.thread.JobExecutor
import com.qifan.githublister.core.thread.UiThread
import com.qifan.githublister.network.RepoService
import kotlinx.android.synthetic.main.fragment_repo_list_layout.*
import org.koin.android.ext.android.inject

/**
 * Created by Qifan on 2019-08-11.
 */
class RepoListFragment : BaseFragment() {
    private val repoService: RepoService by inject()
    private val jobExecutor: JobExecutor by inject()
    private val uiThread: UiThread by inject()
    private lateinit var viewAdapter: RepoListAdapter
    override fun getLayoutId(): Int = R.layout.fragment_repo_list_layout

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpView()
    }

    private fun setUpView() {
        repo_recyclerview.apply {
            layoutManager = LinearLayoutManager(context)
            viewAdapter = RepoListAdapter()
            adapter = viewAdapter
        }
        loadData()
    }

    private fun loadData() {
        repoService.getRepositories(0)
            .subscribeOn(jobExecutor.scheduler)
            .observeOn(uiThread.scheduler)
            .subscribe { list ->
                viewAdapter.setData(list)
            }
    }


}