package com.qifan.githublister.ui.feature.repo.detail.pull

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
import com.qifan.githublister.model.PullModel
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.fragment_list_layout.*
import org.koin.androidx.viewmodel.ext.android.getViewModel
import org.koin.core.parameter.parametersOf
import java.util.concurrent.TimeUnit

/**
 * Created by Qifan on 2019-08-14.
 */
class PullFragment : BaseFragment(), ReactiveBehavior {
    private val safeArgs: PullFragmentArgs by navArgs()
    private lateinit var viewModel: PullViewModel
    private lateinit var viewAdapter: PullAdapter

    override fun getLayoutId(): Int = R.layout.fragment_list_layout

    override fun getMenuId(): Int? = null

    override val behaviors: BehaviorObservers by builder {
        use(
            reactive()
        )
    }

    override fun startObserve(compositeDisposable: CompositeDisposable) {
        val (owner, repo) = safeArgs
        viewModel = getViewModel { parametersOf(owner, repo) }
        compositeDisposable.addAll(
            handleLoading(viewModel).subscribeAndLogError(),
            handleError(viewModel).subscribeAndLogError(),
            getContributors(viewModel).subscribeAndLogError()
        )
    }

    private fun handleLoading(viewModel: PullViewModel) = viewModel.pulls
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


    private fun handleError(viewModel: PullViewModel) = viewModel.pulls
        .error
        .mainThread()
        .doOnNext { (hasError) ->
            if (hasError) Toast.makeText(requireContext(), "Error loading Issues", Toast.LENGTH_SHORT).show()
        }

    private fun getContributors(viewModel: PullViewModel) = viewModel.pulls
        .success
        .mainThread()
        .doOnNext {
            setupView(it)
        }

    private fun setupView(list: List<PullModel>) {
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
            viewAdapter = PullAdapter()
            adapter = viewAdapter
        }
        viewAdapter.setData(list)
    }
}