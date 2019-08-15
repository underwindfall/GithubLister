package com.qifan.githublister.ui.feature.search

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.findNavController
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
import com.qifan.githublister.core.helper.rv.scroll.EndLessScrollListener
import io.reactivex.Completable
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.fragment_list_layout.*
import org.koin.androidx.viewmodel.ext.android.getViewModel
import org.koin.core.parameter.parametersOf
import java.util.concurrent.TimeUnit

/**
 * Created by Qifan on 2019-08-15.
 */
private const val PER_PAGE_SIZE = 100

class SearchFragment : BaseFragment(), ReactiveBehavior {
    private val safeArgs: SearchFragmentArgs by navArgs()
    private lateinit var viewModel: SearchViewModel
    private lateinit var viewAdapter: SearchAdapter

    override fun getLayoutId(): Int = R.layout.fragment_list_layout

    override fun getMenuId(): Int? = null

    override val behaviors: BehaviorObservers by builder {
        use(
            reactive()
        )
    }

    override fun startObserve(compositeDisposable: CompositeDisposable) {
        val (search) = safeArgs
        viewModel = getViewModel { parametersOf(search) }
        compositeDisposable.addAll(
            handleLoading(viewModel).subscribeAndLogError(),
            handleError(viewModel).subscribeAndLogError(),
            getSearchRepos(viewModel).subscribeAndLogError(),
            handleTransactionSelected(viewAdapter).subscribeAndLogError()
        )
    }

    private fun handleLoading(viewModel: SearchViewModel) = viewModel.search
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


    private fun handleError(viewModel: SearchViewModel) = viewModel.search
        .error
        .mainThread()
        .doOnNext { (hasError) ->
            if (hasError) Toast.makeText(requireContext(), "Error loading Search Result", Toast.LENGTH_SHORT).show()
        }

    private fun getSearchRepos(viewModel: SearchViewModel) = viewModel.search
        .success
        .mainThread()
        .doOnNext {
            viewAdapter.setData(it.items)
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
    }

    private fun setupView() {
        val (search) = safeArgs
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
            viewAdapter = SearchAdapter()
            adapter = viewAdapter
            addOnScrollListener(EndLessScrollListener { index ->
                viewModel.fetchSearchRepoList(search, (index % PER_PAGE_SIZE) + 1)
            })
        }
    }

    private fun handleTransactionSelected(adapter: SearchAdapter) = adapter
        .onItemSelected
        .flatMapCompletable { (owner, repo) -> navigateToDetail(owner, repo) }

    private fun navigateToDetail(owner: String, repo: String) = Completable.fromCallable {
        findNavController().navigate(
            SearchFragmentDirections.actionSearchFragmentToRepoDetailFragment(
                owner, repo
            )
        )
    }
}