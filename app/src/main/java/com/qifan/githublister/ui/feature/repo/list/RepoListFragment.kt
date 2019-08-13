package com.qifan.githublister.ui.feature.repo.list

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.findNavController
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
import com.qifan.githublister.core.helper.rv.scroll.EndLessScrollListener
import io.reactivex.Completable
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.fragment_repo_list_layout.*
import org.koin.android.ext.android.inject
import java.util.concurrent.TimeUnit

/**
 * Created by Qifan on 2019-08-11.
 */
class RepoListFragment : BaseFragment(), ReactiveBehavior {
    private val repoListViewModel: RepoListViewModel by inject()
    private var isFirstLoad = true
    private lateinit var viewAdapter: RepoListAdapter
    override fun getLayoutId(): Int = R.layout.fragment_repo_list_layout
    override fun getMenuId(): Int? = null
    override val behaviors: BehaviorObservers by builder {
        use(
            reactive()
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpView()
    }

    private fun setUpView() {
        repo_recyclerview.apply {
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
            viewAdapter = RepoListAdapter()
            adapter = viewAdapter
            addOnScrollListener(EndLessScrollListener { index ->
                repoListViewModel.fetchPublicRepoList(index)
            })
        }
    }

    override fun startObserve(compositeDisposable: CompositeDisposable) {
        compositeDisposable.addAll(
            handleLoading(repoListViewModel).subscribeAndLogError(),
            handleError(repoListViewModel).subscribeAndLogError(),
            getRepoList(repoListViewModel).subscribeAndLogError(),
            handleTransactionSelected(viewAdapter).subscribeAndLogError()
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
        .doOnNext {
            viewAdapter.setData(it)
            isFirstLoad = false
        }

    private fun displayLoadingView(loading: Boolean) {
        when {
            isFirstLoad -> {
                repo_recyclerview.visibility = View.GONE
                progress_bar.visibility = View.VISIBLE
            }
            loading -> {
                repo_recyclerview.visibility = View.VISIBLE
                progress_bar_footer.visibility = View.VISIBLE
            }
            else -> {
                repo_recyclerview.visibility = View.VISIBLE
                progress_bar.visibility = View.GONE
                progress_bar_footer.visibility = View.GONE
            }
        }
    }

    private fun handleTransactionSelected(adapter: RepoListAdapter) = adapter
        .onItemSelected
        .flatMapCompletable { (owner, repo) -> navigateToDetail(owner, repo) }

    private fun navigateToDetail(owner: String, repo: String) = Completable.fromCallable {
        findNavController().navigate(
            RepoListFragmentDirections.actionRepoListFragmentToRepoDetailFragment(
                owner, repo
            )
        )
    }

}