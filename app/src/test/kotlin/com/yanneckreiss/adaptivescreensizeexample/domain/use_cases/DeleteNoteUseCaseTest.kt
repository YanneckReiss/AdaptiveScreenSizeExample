package com.yanneckreiss.adaptivescreensizeexample.domain.use_cases

import com.yanneckreiss.adaptivescreensizeexample.MainDispatcherRule
import com.yanneckreiss.adaptivescreensizeexample.data.repositories.NotesRepository
import com.yanneckreiss.adaptivescreensizeexample.domain.model.Note
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class DeleteNoteUseCaseTest {

    private val testDispatcher = StandardTestDispatcher()
    private lateinit var notesRepository: NotesRepository
    private lateinit var deleteNotesUseCase: DeleteNoteUseCase

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule(testDispatcher)

    @Before
    fun setUp() {
        notesRepository = NotesRepository(testDispatcher)
        deleteNotesUseCase = DeleteNoteUseCase(notesRepository)
    }

    @Test
    fun `Note gets successfully deleted`() = runTest {

        // Arrange
        val notes: List<Note> = notesRepository.getAll()
        val exampleNote: Note = notes.first()

        // Act
        deleteNotesUseCase.call(exampleNote.uid)

        // Assert
        val notesAfterDeletion: List<Note> = notesRepository.getAll()

        assert(notes.size - 1 == notesAfterDeletion.size)
        assert(notesAfterDeletion.none { note -> note.uid == exampleNote.uid })
    }
}
