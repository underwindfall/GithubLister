package com.qifan.githublister.ui.feature.repo.detail

import android.view.View
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.qifan.githublister.R
import com.qifan.githublister.core.base.BaseFragment
import com.qifan.githublister.core.behavior.BehaviorObservers
import com.qifan.githublister.core.behavior.builder
import com.qifan.githublister.core.behavior.reactive.ReactiveBehavior
import com.qifan.githublister.core.behavior.reactive.reactive
import com.qifan.githublister.core.extension.loadAvatar
import com.qifan.githublister.core.extension.reactive.mainThread
import com.qifan.githublister.core.extension.reactive.subscribeAndLogError
import com.qifan.githublister.model.RepoInfoModel
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.fragment_repo_detail_layout.*
import org.koin.androidx.viewmodel.ext.android.getViewModel
import org.koin.core.parameter.parametersOf
import java.util.concurrent.TimeUnit

/**
 * Created by Qifan on 2019-08-12.
 */
class RepoDetailFragment : BaseFragment(), ReactiveBehavior {
    private val safeArgs: RepoDetailFragmentArgs by navArgs()
    private lateinit var viewModel: RepoDetailViewModel
    override fun getLayoutId(): Int = R.layout.fragment_repo_detail_layout
    override fun getMenuId(): Int? = R.menu.branch_menu
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
            getRepoInfo(viewModel).subscribeAndLogError()
        )
    }

    private fun handleLoading(viewModel: RepoDetailViewModel) = viewModel.repoInfo
        .loading
        .debounce(200, TimeUnit.MILLISECONDS)
        .mainThread()
        .doOnNext { loading ->
            displayLoadingView(loading)
        }

    private fun displayLoadingView(loading: Boolean) {
        if (loading) {
            spinner.visibility = View.VISIBLE
            container.visibility = View.GONE
        } else {
            spinner.visibility = View.GONE
            container.visibility = View.VISIBLE
        }
    }


    private fun handleError(viewModel: RepoDetailViewModel) = viewModel.repoInfo
        .error
        .mainThread()
        .doOnNext { (hasError) ->
            if (hasError) Toast.makeText(requireContext(), "Error loading Repo List", Toast.LENGTH_SHORT).show()
        }

    private fun getRepoInfo(viewModel: RepoDetailViewModel) = viewModel.repoInfo
        .success
        .mainThread()
        .doOnNext {
            setupView(it)
        }

    private fun setupView(model: RepoInfoModel) {
        author_avatar.loadAvatar(model.owner.avatar_url)
        repo_title.text = model.full_name
        model.description?.run {
            repo_des.text = this
        } ?: repo_des.apply {
            visibility = View.GONE
        }
        repo_fork.text = getString(R.string.fork, if (model.fork) "YES" else "NO")
        repo_stars.text = getString(R.string.stars, model.stargazersCount)
        repo_forks.text = getString(R.string.forks, model.forksCount)
        repo_watches.text = getString(R.string.watches, model.watchesCount)
        contributors_container.setOnClickListener(
            Navigation.createNavigateOnClickListener(
                RepoDetailFragmentDirections.actionRepoDetailFragmentToContributorFragment(
                    model.owner.login,
                    model.name
                )
            )
        )
    }

}