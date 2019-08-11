package com.qifan.githublister.core.di.modules

import com.qifan.githublister.ui.repo.list.RepoListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by Qifan on 2019-08-11.
 */
val viewModelModules = module {
    viewModel { RepoListViewModel(get()) }
}