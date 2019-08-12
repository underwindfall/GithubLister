package com.qifan.githublister.core.base

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.qifan.githublister.core.behavior.BehaviorConsumer

/**
 * Created by Qifan on 2019-08-11.
 */
abstract class BaseFragment : Fragment(), BehaviorConsumer {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getMenuId()?.run { setHasOptionsMenu(true) }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(getLayoutId(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycle.consumeBehaviors()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
        getMenuId()?.run { inflater.inflate(this, menu) }
    }

    override fun onDestroyView() {
        lifecycle.clearBehaviors()
        super.onDestroyView()
    }

    abstract fun getLayoutId(): Int
    abstract fun getMenuId(): Int?
}