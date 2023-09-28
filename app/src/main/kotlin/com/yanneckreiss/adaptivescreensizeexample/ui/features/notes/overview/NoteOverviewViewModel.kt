package com.yanneckreiss.adaptivescreensizeexample.ui.features.notes.overview

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yanneckreiss.adaptivescreensizeexample.domain.use_cases.WatchSortedNotesUseCase
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class NoteOverviewViewModel(
    watchSortedNotesUseCase: WatchSortedNotesUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(NotesOverviewState())
    val state: StateFlow<NotesOverviewState> = _state.combine(watchSortedNotesUseCase.call()) { state, notes ->
        state.copy(notes = notes.toImmutableList())
    }.stateIn(
        viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000L),
        initialValue = NotesOverviewState()
    )
}
