@file:OptIn(KoinExperimentalAPI::class)

package com.yanneckreiss.adaptivescreensizeexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.yanneckreiss.adaptivescreensizeexample.ui.NotesApp
import com.yanneckreiss.adaptivescreensizeexample.ui.base.composables.isWideDisplay
import com.yanneckreiss.adaptivescreensizeexample.ui.theme.FoldableExampleTheme
import org.koin.androidx.compose.KoinAndroidContext
import org.koin.core.annotation.KoinExperimentalAPI

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FoldableExampleTheme {
                val isWideDisplay: Boolean = isWideDisplay(this)

                KoinAndroidContext {
                    NotesApp(
                        isWideDisplay = isWideDisplay
                    )
                }
            }
        }
    }
}
