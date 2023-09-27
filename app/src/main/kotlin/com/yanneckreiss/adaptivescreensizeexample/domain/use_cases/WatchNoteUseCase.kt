package com.yanneckreiss.adaptivescreensizeexample.domain.use_cases

import com.yanneckreiss.adaptivescreensizeexample.data.repositories.NotesRepository
import com.yanneckreiss.adaptivescreensizeexample.domain.model.Note
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.koin.core.annotation.Factory
import java.util.UUID

@Factory
class WatchNoteUseCase(
    private val notesRepository: NotesRepository,
) {

    fun call(noteUid: UUID): Flow<Note?> = notesRepository.watchNotes().map { notes ->
        notes.firstOrNull { note -> note.uid == noteUid }
    }
}
