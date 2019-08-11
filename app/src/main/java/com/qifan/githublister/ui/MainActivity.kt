package com.qifan.githublister.ui

import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import com.qifan.githublister.R
import com.qifan.githublister.core.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {
    private lateinit var host: NavHostFragment
    override fun getLayoutId(): Int = R.layout.activity_main
    override fun getMenuId(): Int? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        host = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        host.navController.addOnDestinationChangedListener { controller, destination, arguments ->
            toolbar.title = destination.label
        }
    }
}
