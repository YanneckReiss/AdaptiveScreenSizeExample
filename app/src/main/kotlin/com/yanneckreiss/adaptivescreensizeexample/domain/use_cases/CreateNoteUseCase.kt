package com.yanneckreiss.adaptivescreensizeexample.domain.use_cases

import com.yanneckreiss.adaptivescreensizeexample.data.repositories.NotesRepository
import org.koin.core.annotation.Factory
import java.time.ZonedDateTime

@Factory
class CreateNoteUseCase(
    private val notesRepository: NotesRepository,
) {

    suspend fun call(text: String) {
        notesRepository.addNote(
            text = text,
            timeOfCreation = ZonedDateTime.now()
        )
    }
}
