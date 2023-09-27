@file:OptIn(ExperimentalMaterial3WindowSizeClassApi::class)

package com.yanneckreiss.adaptivescreensizeexample.ui.features.notes

import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.runtime.Composable
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.annotation.Destination
import com.yanneckreiss.adaptivescreensizeexample.ui.base.composables.isWideDisplay
import com.yanneckreiss.adaptivescreensizeexample.ui.base.navigation.graphs.TopLevelNavGraph
import com.yanneckreiss.adaptivescreensizeexample.ui.features.NavGraphs
import com.yanneckreiss.adaptivescreensizeexample.ui.features.notes.two_pane.NotesTwoPaneScreen

@TopLevelNavGraph(start = true)
@Destination
@Composable
fun NotesTab() {

    val showTwoPaneLayout: Boolean = isWideDisplay()

    NotesContent(
        showTwoPaneLayout = showTwoPaneLayout
    )
}

@Composable
private fun NotesContent(
    showTwoPaneLayout: Boolean,
) {

    if (showTwoPaneLayout) {
        NotesTwoPaneScreen()
    } else {
        DestinationsNavHost(navGraph = NavGraphs.notes)
    }
}
