package com.yanneckreiss.adaptivescreensizeexample.ui.features.settings

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier,
) {
    SettingsContent(
        modifier = modifier.fillMaxSize()
    )
}

@Composable
private fun SettingsContent(
    modifier: Modifier = Modifier,
) {

    Box(
        modifier = modifier,
        contentAlignment = androidx.compose.ui.Alignment.Center
    ) {
        Text(text = "Settings")
    }
}
