package com.qifan.githublister.ui.repo.list

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.qifan.githublister.R
import com.qifan.githublister.core.base.BaseFragment
import com.qifan.githublister.core.extension.reactive.mainThread
import com.qifan.githublister.core.extension.reactive.subscribeAndLogError
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.fragment_repo_list_layout.*
import org.koin.android.ext.android.inject
import java.util.concurrent.TimeUnit

/**
 * Created by Qifan on 2019-08-11.
 */
class RepoListFragment : BaseFragment() {
    private val compositeDisposable = CompositeDisposable()
    private val repoListViewModel: RepoListViewModel by inject()
    private lateinit var viewAdapter: RepoListAdapter
    override fun getLayoutId(): Int = R.layout.fragment_repo_list_layout
    override fun getMenuId(): Int? = null

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
        startObserve(repoListViewModel)
    }

    private fun startObserve(repoListViewModel: RepoListViewModel) {
        compositeDisposable.addAll(
            handleLoading(repoListViewModel).subscribeAndLogError(),
            handleError(repoListViewModel).subscribeAndLogError(),
            getRepoList(repoListViewModel).subscribeAndLogError()
        )
    }

    private fun handleLoading(viewModel: RepoListViewModel) = viewModel.repos
        .loading
        .debounce(200, TimeUnit.MILLISECONDS)
        .mainThread()
        .doOnNext { loading ->
            displayLoadingView(loading)
        }


    private fun handleError(viewModel: RepoListViewModel) = viewModel.repos
        .error
        .mainThread()
        .doOnNext { (hasError) ->
            if (hasError) Toast.makeText(requireContext(), "Error loading Repo List", Toast.LENGTH_SHORT).show()
        }

    private fun getRepoList(viewModel: RepoListViewModel) = viewModel.repos
        .success
        .mainThread()
        .doOnNext { viewAdapter.setData(it) }

    private fun displayLoadingView(loading: Boolean) {
        if (loading) {
            repo_recyclerview.visibility = View.GONE
            progress_bar.visibility = View.VISIBLE
        } else {
            repo_recyclerview.visibility = View.VISIBLE
            progress_bar.visibility = View.GONE
        }
    }

    private fun stopObserve() {
        compositeDisposable.clear()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        stopObserve()
    }

}