package com.yanneckreiss.adaptivescreensizeexample.ui.base.navigation.graphs

import com.ramcosta.composedestinations.annotation.NavGraph
import com.ramcosta.composedestinations.annotation.RootNavGraph

@RootNavGraph(start = true)
@NavGraph
annotation class TopLevelNavGraph(
    val start: Boolean = false
)
