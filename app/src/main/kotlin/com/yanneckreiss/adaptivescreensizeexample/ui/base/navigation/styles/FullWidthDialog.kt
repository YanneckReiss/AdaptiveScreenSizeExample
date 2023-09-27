package com.yanneckreiss.adaptivescreensizeexample.ui.base.navigation.styles

import androidx.compose.ui.window.DialogProperties
import com.ramcosta.composedestinations.spec.DestinationStyle

object FullWidthDialog : DestinationStyle.Dialog {
    override val properties: DialogProperties
        get() = DialogProperties(
            usePlatformDefaultWidth = false
        )
}
