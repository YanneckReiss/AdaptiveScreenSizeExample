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

class WatchSortedNotesUseCaseTest {

    private val testDispatcher = StandardTestDispatcher()
    private lateinit var notesRepository: NotesRepository
    private lateinit var watchSortedNotesUseCase: WatchSortedNotesUseCase

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule(testDispatcher)

    @Before
    fun setUp() {
        notesRepository = NotesRepository(testDispatcher)
        watchSortedNotesUseCase = WatchSortedNotesUseCase(notesRepository)
    }

    @Test
    fun `All present notes are contained in the exposed Flow and sorted correctly`() = runTest {

        // Arrange
        val expectedNotes: List<Note> = notesRepository.getAll()

        // Act
        watchSortedNotesUseCase.call().test {
            val notesFromFlow: List<Note> = awaitItem()

            // Assert
            assert(expectedNotes.sortedByDescending { expectedNote -> expectedNote.dateOfCreation } == notesFromFlow)
        }
    }
}