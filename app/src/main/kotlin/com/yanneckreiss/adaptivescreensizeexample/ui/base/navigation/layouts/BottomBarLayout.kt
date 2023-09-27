package com.yanneckreiss.adaptivescreensizeexample.ui.base.navigation.layouts

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.ramcosta.composedestinations.spec.DestinationSpec
import com.ramcosta.composedestinations.utils.currentDestinationAsState
import com.yanneckreiss.adaptivescreensizeexample.ui.base.navigation.Tab
import com.yanneckreiss.adaptivescreensizeexample.ui.base.navigation.navigationTabs

@Composable
fun BottomBarLayout(
    navController: NavController,
    content: @Composable (PaddingValues) -> Unit,
) {
    Scaffold(
        bottomBar = { AppNavigationBar(navController = navController) },
        content = content
    )
}

@Composable
private fun AppNavigationBar(
    navController: NavController,
    modifier: Modifier = Modifier,
) {
    NavigationBar(
        modifier = modifier.fillMaxWidth()
    ) {
        val currentDestination: DestinationSpec<*>? by navController.currentDestinationAsState()

        navigationTabs.forEach { tab: Tab ->
            val isSelected: Boolean = remember(currentDestination?.route) {
                currentDestination?.route?.startsWith(tab.destination.route) == true
            }

            NavigationBarItem(
                selected = isSelected,
                onClick = {
                    if (!isSelected) {
                        navController.navigate(tab.destination.route)
                    }
                },
                icon = tab.icon,
                label = tab.label,
            )
        }
    }
}
