package com.qifan.githublister.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.navigation.fragment.NavHostFragment
import com.qifan.githublister.R
import com.qifan.githublister.core.base.BaseActivity
import com.qifan.githublister.core.extension.isStartDestination
import com.qifan.githublister.ui.feature.search.SearchFragmentArgs
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {
    private lateinit var host: NavHostFragment
    override fun getLayoutId(): Int = R.layout.activity_main
    override fun getMenuId(): Int? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(toolbar)
        host = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        host.navController.addOnDestinationChangedListener { controller, destination, arguments ->
            toolbar.title = destination.label
            if (controller.isStartDestination(destination)) {
                hideNavIcon()
                showSearchView()
            } else {
                showNavIcon()
                hideSearchView()
            }
        }

    }

    private fun hideNavIcon() {
        toolbar.apply {
            navigationIcon = null
            setNavigationOnClickListener(null)
        }
    }

    private fun showNavIcon() {
        toolbar.apply {
            navigationIcon = getDrawable(R.drawable.ic_arrow_back_black_24dp)
            setNavigationOnClickListener { host.navController.popBackStack() }
        }
    }

    private fun showSearchView() {
        search_view.apply {
            visibility = View.VISIBLE
            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextChange(newText: String?): Boolean {
                    return true
                }

                override fun onQueryTextSubmit(query: String?): Boolean {
                    if (!query.isNullOrEmpty()) {
                        host.navController.navigate(R.id.searchFragment, SearchFragmentArgs(query).toBundle())
                    }
                    return true
                }

            })
        }
    }

    private fun hideSearchView() {
        search_view.visibility = View.GONE
    }
}
