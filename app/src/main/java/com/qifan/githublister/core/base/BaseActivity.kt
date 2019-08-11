package com.qifan.githublister.core.base

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity

/**
 * Created by Qifan on 2019-08-11.
 */
abstract class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        val menuId = getMenuId()
        return menuId?.let {
            menuInflater.inflate(menuId, menu)
            true
        } ?: false
    }

    abstract fun getLayoutId(): Int
    abstract fun getMenuId(): Int?
}