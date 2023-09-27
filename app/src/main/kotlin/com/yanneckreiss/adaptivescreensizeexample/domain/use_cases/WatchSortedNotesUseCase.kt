package com.yanneckreiss.adaptivescreensizeexample.domain.use_cases

import com.yanneckreiss.adaptivescreensizeexample.data.repositories.NotesRepository
import com.yanneckreiss.adaptivescreensizeexample.domain.model.Note
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.koin.core.annotation.Factory

@Factory
class WatchSortedNotesUseCase(
    private val notesRepository: NotesRepository,
) {

    fun call(): Flow<List<Note>> = notesRepository.watchNotes().map { notes ->
        println("----> Sorting list!")
        notes.sortedByDescending { note -> note.dateOfCreation }
    }
}
