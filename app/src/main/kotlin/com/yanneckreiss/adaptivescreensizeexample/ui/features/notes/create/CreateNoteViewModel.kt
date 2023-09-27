package com.yanneckreiss.adaptivescreensizeexample.ui.features.notes.create

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yanneckreiss.adaptivescreensizeexample.domain.use_cases.CreateNoteUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class CreateNoteViewModel(
    private val createNoteUseCase: CreateNoteUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(CreateNoteState())
    val state: StateFlow<CreateNoteState> = _state.asStateFlow()

    fun onTextChange(text: String) {
        _state.value = _state.value.copy(
            currentText = text
        )
    }

    fun createNote() {
        val currentText: String = _state.value.currentText

        if (currentText.isBlank()) return

        viewModelScope.launch {
            createNoteUseCase.call(currentText)

            _state.update { currentState ->
                currentState.copy(
                    noteCreated = true
                )
            }
        }
    }
}