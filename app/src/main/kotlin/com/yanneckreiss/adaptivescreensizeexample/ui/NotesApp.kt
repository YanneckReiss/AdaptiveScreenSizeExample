package com.yanneckreiss.adaptivescreensizeexample.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.movableContentOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.ramcosta.composedestinations.DestinationsNavHost
import com.yanneckreiss.adaptivescreensizeexample.ui.base.navigation.layouts.BottomBarLayout
import com.yanneckreiss.adaptivescreensizeexample.ui.base.navigation.layouts.NavigationRailLayout
import com.yanneckreiss.adaptivescreensizeexample.ui.features.NavGraphs

@Composable
fun NotesApp(isWideDisplay: Boolean) {

    NotesAppContent(isWideDisplay = isWideDisplay)
}

@Composable
private fun NotesAppContent(
    isWideDisplay: Boolean,
) {
    val navController: NavHostController = rememberNavController()

    val navHost = remember {
        movableContentOf<PaddingValues> { innerPadding ->
            DestinationsNavHost(
                modifier = Modifier.padding(innerPadding),
                navGraph = NavGraphs.root,
                navController = navController,
            )
        }
    }

    if (isWideDisplay) {
        NavigationRailLayout(
            navController = navController,
        ) {
            navHost(PaddingValues())
        }
    } else {
        BottomBarLayout(
            navController = navController,
        ) { innerPadding ->
            navHost(innerPadding)
        }
    }
}
