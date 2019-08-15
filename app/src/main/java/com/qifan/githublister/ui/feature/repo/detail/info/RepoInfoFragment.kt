package com.qifan.githublister.ui.feature.repo.detail.info

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.qifan.githublister.R
import com.qifan.githublister.core.base.BaseFragment
import com.qifan.githublister.core.behavior.BehaviorObservers
import com.qifan.githublister.core.behavior.builder
import com.qifan.githublister.core.behavior.reactive.ReactiveBehavior
import com.qifan.githublister.core.behavior.reactive.reactive
import com.qifan.githublister.core.extension.reactive.mainThread
import com.qifan.githublister.core.extension.reactive.subscribeAndLogError
import com.qifan.githublister.core.helper.rv.decorator.MarginItemDecorator
import com.qifan.githublister.ui.feature.repo.detail.info.branch.BranchViewModel
import com.qifan.githublister.ui.feature.repo.detail.info.contributor.ContributorViewModel
import com.qifan.githublister.ui.feature.repo.detail.info.issue.IssueViewModel
import com.qifan.githublister.ui.feature.repo.detail.info.pull.PullViewModel
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.fragment_list_layout.*
import org.koin.androidx.viewmodel.ext.android.getViewModel
import org.koin.core.parameter.parametersOf
import java.util.concurrent.TimeUnit

/**
 * Created by Qifan on 2019-08-15.
 */

class RepoInfoFragment : BaseFragment(), ReactiveBehavior {
    private val safeArgs: RepoInfoFragmentArgs by navArgs()
    private lateinit var viewModel: RepoInfoViewModel<*>
    private lateinit var viewAdapter: RepoInfoAdapter

    override fun getLayoutId(): Int = R.layout.fragment_list_layout

    override fun getMenuId(): Int? = null

    override val behaviors: BehaviorObservers by builder {
        use(
            reactive()
        )
    }

    override fun startObserve(compositeDisposable: CompositeDisposable) {
        compositeDisposable.addAll(
            handleLoading(viewModel).subscribeAndLogError(),
            handleError(viewModel).subscribeAndLogError(),
            getContributors(viewModel).subscribeAndLogError()
        )
    }

    private fun handleLoading(viewModel: RepoInfoViewModel<*>) = viewModel.dataList
        .loading
        .debounce(200, TimeUnit.MILLISECONDS)
        .mainThread()
        .doOnNext { loading ->
            displayLoadingView(loading)
        }

    private fun displayLoadingView(loading: Boolean) {
        if (loading) {
            spinner.visibility = View.VISIBLE
            recycler_view.visibility = View.GONE
        } else {
            spinner.visibility = View.GONE
            recycler_view.visibility = View.VISIBLE
        }
    }


    private fun handleError(viewModel: RepoInfoViewModel<*>) = viewModel.dataList
        .error
        .mainThread()
        .doOnNext { (hasError) ->
            if (hasError) Toast.makeText(requireContext(), "Error loading Issues", Toast.LENGTH_SHORT).show()
        }

    private fun getContributors(viewModel: RepoInfoViewModel<*>) = viewModel.dataList
        .success
        .mainThread()
        .doOnNext {
            viewAdapter.setData(it)
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        getData()
    }

    private fun setupView() {
        recycler_view.apply {
            LinearLayoutManager(context).apply {
                layoutManager = this
                with(
                    MarginItemDecorator(
                        MarginItemDecorator.Space(
                            space = resources.getDimension(R.dimen.list_gap_default).toInt(),
                            marginTop = resources.getDimension(R.dimen.common_margin_normal).toInt()
                        ),
                        MarginItemDecorator.Position.VERTICAL
                    )
                ) {
                    addItemDecoration(this)
                }
            }
            viewAdapter = RepoInfoAdapter()
            adapter = viewAdapter
        }
    }

    private fun getData() {
        val (owner, repo, type) = safeArgs
        when (type) {
            PR -> viewModel = getViewModel(PullViewModel::class) { parametersOf(type) }
            ISSUE -> viewModel = getViewModel(IssueViewModel::class) { parametersOf(type) }
            BRANCH -> viewModel = getViewModel(BranchViewModel::class) { parametersOf(type) }
            CONTRIBUTOR -> viewModel = getViewModel(ContributorViewModel::class) { parametersOf(type) }
        }
        viewModel.getDataList(owner, repo)
    }
}