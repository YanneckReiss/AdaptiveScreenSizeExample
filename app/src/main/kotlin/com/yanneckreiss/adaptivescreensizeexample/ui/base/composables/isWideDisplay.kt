@file:OptIn(ExperimentalMaterial3WindowSizeClassApi::class)

package com.yanneckreiss.adaptivescreensizeexample.ui.base.composables

import android.app.Activity
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext

@Composable
fun isWideDisplay(
    activity: Activity = LocalContext.current as Activity,
): Boolean {

    val windowSizeClass = calculateWindowSizeClass(activity)
    val showTwoPaneLayout: Boolean by remember(windowSizeClass) {
        derivedStateOf {
            windowSizeClass.widthSizeClass >= WindowWidthSizeClass.Medium
        }
    }

    return showTwoPaneLayout
}