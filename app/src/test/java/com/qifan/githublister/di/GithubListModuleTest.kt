package com.qifan.githublister.di

import android.app.Application
import com.qifan.githublister.core.di.appModule
import com.qifan.githublister.ui.feature.repo.detail.RepoDetailViewModel
import com.qifan.githublister.ui.feature.repo.detail.info.BRANCH
import com.qifan.githublister.ui.feature.repo.detail.info.CONTRIBUTOR
import com.qifan.githublister.ui.feature.repo.detail.info.ISSUE
import com.qifan.githublister.ui.feature.repo.detail.info.PR
import com.qifan.githublister.ui.feature.repo.detail.info.branch.BranchViewModel
import com.qifan.githublister.ui.feature.repo.detail.info.contributor.ContributorViewModel
import com.qifan.githublister.ui.feature.repo.detail.info.issue.IssueViewModel
import com.qifan.githublister.ui.feature.repo.detail.info.pull.PullViewModel
import com.qifan.githublister.ui.feature.search.SearchViewModel
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.core.parameter.parametersOf
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.check.checkModules
import org.mockito.Mockito.mock

/**
 * Created by Qifan on 2019-08-16.
 */
class GithubListModuleTest : KoinTest {

    private val mockedAndroidContext = module {
        single { mock(Application::class.java) }
    }


    @Test
    fun testViewModelModules() {
        startKoin {
            printLogger(Level.DEBUG)
            modules(appModule + mockedAndroidContext)
        }.checkModules {
            create<RepoDetailViewModel> { parametersOf("", "") }
            create<ContributorViewModel> { parametersOf(CONTRIBUTOR) }
            create<BranchViewModel> { parametersOf(BRANCH) }
            create<IssueViewModel> { parametersOf(ISSUE) }
            create<PullViewModel> { parametersOf(PR) }
            create<SearchViewModel> { parametersOf("") }
        }
    }

}
