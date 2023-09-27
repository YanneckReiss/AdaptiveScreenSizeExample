package com.yanneckreiss.adaptivescreensizeexample.domain.use_cases

import app.cash.turbine.test
import com.yanneckreiss.adaptivescreensizeexample.MainDispatcherRule
import com.yanneckreiss.adaptivescreensizeexample.data.repositories.NotesRepository
import com.yanneckreiss.adaptivescreensizeexample.domain.model.Note
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class WatchNoteUseCaseTest {

    private val testDispatcher = StandardTestDispatcher()
    private lateinit var notesRepository: NotesRepository
    private lateinit var watchNoteUseCase: WatchNoteUseCase

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule(testDispatcher)

    @Before
    fun setUp() {
        notesRepository = NotesRepository(testDispatcher)
        watchNoteUseCase = WatchNoteUseCase(notesRepository)
    }

    @Test
    fun `Note gets exposed in Flow`() = runTest {

        // Arrange
        val currentNotes: List<Note> = notesRepository.getAll()
        val expectedNote: Note = currentNotes.first()

        // Act
        watchNoteUseCase.call(expectedNote.uid).test {
            val noteFromFlow: Note? = awaitItem()

            // Assert
            assert(expectedNote == noteFromFlow)
        }
    }

    @Test
    fun `Null is exposed instead if Note gets deleted`() = runTest {

        // Arrange
        val currentNotes: List<Note> = notesRepository.getAll()
        val expectedNote: Note = currentNotes.first()
        notesRepository.deleteNote(expectedNote.uid)

        // Act & Assert
        watchNoteUseCase.call(expectedNote.uid).test {
            val noteFromFlow: Note? = awaitItem()
            assert(noteFromFlow == null)
        }
    }
}
