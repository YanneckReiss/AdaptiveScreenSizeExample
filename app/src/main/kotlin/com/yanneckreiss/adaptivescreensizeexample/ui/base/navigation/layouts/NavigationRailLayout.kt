package com.yanneckreiss.adaptivescreensizeexample.ui.base.navigation.layouts

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ramcosta.composedestinations.navigation.navigate
import com.ramcosta.composedestinations.spec.DestinationSpec
import com.ramcosta.composedestinations.utils.currentDestinationAsState
import com.yanneckreiss.adaptivescreensizeexample.ui.base.navigation.navigationTabs
import com.yanneckreiss.adaptivescreensizeexample.ui.features.destinations.CreateNoteNavigationDestination

@Composable
fun NavigationRailLayout(
    navController: NavController,
    content: @Composable () -> Unit,
) {
    Row(
        modifier = Modifier.fillMaxHeight(),
    ) {
        AppNavigationRail(
            navController = navController,
        )

        content()
    }
}

@Composable
private fun AppNavigationRail(
    navController: NavController,
    modifier: Modifier = Modifier,
) {

    val currentDestination: DestinationSpec<*>? by navController.currentDestinationAsState()

    NavigationRail(
        modifier = modifier.fillMaxHeight(),
        header = {
            Spacer(modifier = Modifier.padding(8.dp))
            FloatingActionButton(onClick = {
                navController.navigate(CreateNoteNavigationDestination())
            }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add note")
            }
        },
        containerColor = MaterialTheme.colorScheme.inverseOnSurface
    ) {

        navigationTabs.forEach { tab ->
            val tabRoute: String = tab.destination.route
            val isSelected: Boolean = remember(currentDestination?.route) {
                currentDestination?.route?.startsWith(tabRoute) == true
            }

            NavigationRailItem(
                selected = isSelected,
                onClick = {
                    if (!isSelected) {
                        navController.navigate(tabRoute)
                    }
                },
                icon = tab.icon,
                label = tab.label,
            )
        }
    }
}
