package com.qifan.githublister.helper

import com.qifan.githublister.core.BASE_URL
import com.qifan.githublister.core.thread.executor.PostExecutionThread
import com.qifan.githublister.core.thread.executor.ThreadExecutor
import com.qifan.githublister.datasource.repo.RepoRemoteDataSource
import com.qifan.githublister.network.RepoService
import com.qifan.githublister.repository.repo.RepoListRepository
import com.qifan.githublister.ui.feature.repo.list.RepoListViewModel
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Qifan on 2019-08-15.
 */
val netWorkModules = module {
    single<Retrofit> {
        Retrofit.Builder()
            .client(OkHttpClient.Builder().build())
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(get()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }
}

val remoteModules = module {
    single<RepoService> {
        get<Retrofit>().create(RepoService::class.java)
    }
}

val dataSourceModules = module {
    single { RepoRemoteDataSource(get()) }
}

val repositoryModules = module {
    single { RepoListRepository(get()) }
}
val schedulerModules = module {
    single { TestJobExecutor() } bind ThreadExecutor::class
    single { TestUiThread() } bind PostExecutionThread::class
}
val viewModelModules = module {
    viewModel { RepoListViewModel(get()) }
}

class TestJobExecutor : ThreadExecutor {
    override val scheduler: Scheduler = Schedulers.trampoline()
}

class TestUiThread : PostExecutionThread {
    override val scheduler: Scheduler = Schedulers.trampoline()
}

val testModule = listOf(
    netWorkModules, dataSourceModules, repositoryModules, schedulerModules, viewModelModules, remoteModules
)