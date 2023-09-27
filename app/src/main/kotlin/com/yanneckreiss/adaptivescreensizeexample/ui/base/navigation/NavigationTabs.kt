package com.yanneckreiss.adaptivescreensizeexample.ui.base.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import com.yanneckreiss.adaptivescreensizeexample.ui.features.destinations.DirectionDestination
import com.yanneckreiss.adaptivescreensizeexample.ui.features.destinations.NotesTabDestination
import com.yanneckreiss.adaptivescreensizeexample.ui.features.destinations.SettingsTabDestination
import kotlinx.collections.immutable.persistentListOf

@Immutable
data class Tab(
    val destination: DirectionDestination,
    val icon: @Composable () -> Unit,
    val label: @Composable () -> Unit,
)

@Stable
val navigationTabs = persistentListOf(
    Tab(
        destination = NotesTabDestination,
        icon = {
            Icon(
                imageVector = Icons.Default.List,
                contentDescription = "Notes"
            )
        },
        label = {
            Text(text = "Notes")
        }
    ),
    Tab(
        destination = SettingsTabDestination,
        icon = {
            Icon(
                imageVector = Icons.Default.Settings,
                contentDescription = "Settings"
            )
        },
        label = {
            Text(text = "Settings")
        }
    ),
)
