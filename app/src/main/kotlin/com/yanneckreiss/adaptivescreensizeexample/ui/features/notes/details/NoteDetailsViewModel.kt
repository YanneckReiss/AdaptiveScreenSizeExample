package com.yanneckreiss.adaptivescreensizeexample.ui.features.notes.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yanneckreiss.adaptivescreensizeexample.domain.use_cases.WatchNoteUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import org.koin.android.annotation.KoinViewModel
import org.koin.core.annotation.InjectedParam
import java.util.UUID

@KoinViewModel
class NoteDetailsViewModel(
    @InjectedParam notesUid: UUID?,
    watchNotesUseCase: WatchNoteUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(NoteDetailsState())
    val state: StateFlow<NoteDetailsState> = when {
        notesUid != null -> {
            _state.combine(watchNotesUseCase.call(notesUid)) { state, note ->
                state.copy(note = note)
            }.stateIn(
                viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000L),
                initialValue = NoteDetailsState()
            )
        }

        else -> {
            _state.asStateFlow()
        }
    }
}
