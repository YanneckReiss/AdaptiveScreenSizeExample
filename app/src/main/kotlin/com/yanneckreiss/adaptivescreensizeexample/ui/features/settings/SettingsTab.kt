@file:OptIn(ExperimentalMaterial3Api::class)

package com.yanneckreiss.adaptivescreensizeexample.ui.features.settings

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ramcosta.composedestinations.annotation.Destination
import com.yanneckreiss.adaptivescreensizeexample.ui.base.navigation.graphs.TopLevelNavGraph

@TopLevelNavGraph
@Destination
@Composable
fun SettingsTab() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Settings") }
            )
        }
    ) { innerPadding ->
        SettingsScreen(
            modifier = Modifier.padding(innerPadding)
        )
    }
}
