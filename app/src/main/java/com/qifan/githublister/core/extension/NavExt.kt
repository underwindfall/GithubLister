package com.qifan.githublister.core.extension

import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph

/**
 * Created by Qifan on 2019-08-13.
 */
fun NavController.findStartDestination(): NavDestination {
    var startDestination: NavDestination? = graph
    while (startDestination is NavGraph) {
        val parent = startDestination as NavGraph?
        startDestination = parent!!.findNode(parent.startDestination)
    }
    return startDestination ?: graph
}

fun NavController.isStartDestination(destination: NavDestination): Boolean {
    return findStartDestination().id == destination.id
}