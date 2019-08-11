package com.qifan.githublister.core.base

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment

/**
 * Created by Qifan on 2019-08-11.
 */
abstract class BaseFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getMenuId()?.run { setHasOptionsMenu(true) }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(getLayoutId(), container, false)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
        getMenuId()?.run { inflater.inflate(this, menu) }
    }


    abstract fun getLayoutId(): Int
    abstract fun getMenuId(): Int?
}