package com.yanneckreiss.adaptivescreensizeexample.domain.use_cases

import com.yanneckreiss.adaptivescreensizeexample.data.repositories.NotesRepository
import org.koin.core.annotation.Factory
import java.util.UUID

@Factory
class DeleteNoteUseCase(
    private val notesRepository: NotesRepository,
) {

    suspend fun call(noteUid: UUID) {
        notesRepository.deleteNote(uid = noteUid)
    }
}
