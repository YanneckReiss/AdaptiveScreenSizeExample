package com.yanneckreiss.adaptivescreensizeexample.ui.features.notes.two_pane

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import org.koin.android.annotation.KoinViewModel
import java.util.UUID

@KoinViewModel
class NotesTwoPaneViewModel : ViewModel() {

    private val _state = MutableStateFlow(NotesTwoPaneState())
    val state = _state.asStateFlow()

    fun selectNoteUid(uid: UUID) {
        _state.update { state ->
            state.copy(
                selectedNoteUid = uid
            )
        }
    }
}
