@file:OptIn(ExperimentalCoroutinesApi::class)

package com.yanneckreiss.adaptivescreensizeexample.domain.use_cases

import app.cash.turbine.test
import com.yanneckreiss.adaptivescreensizeexample.MainDispatcherRule
import com.yanneckreiss.adaptivescreensizeexample.data.repositories.NotesRepository
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CreateNoteUseCaseTest {

    private val testDispatcher = StandardTestDispatcher()
    private lateinit var notesRepository: NotesRepository
    private lateinit var createNoteUseCase: CreateNoteUseCase

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule(testDispatcher)

    @Before
    fun setUp() {
        notesRepository = NotesRepository(testDispatcher)
        createNoteUseCase = CreateNoteUseCase(notesRepository)
    }

    @Test
    fun `A new note gets added to the stored notes`() = runTest {

        // Arrange
        val exampleText = "AbcAbcAbcAbcAbcAbc"

        // Act
        createNoteUseCase.call(exampleText)

        // Assert
        notesRepository.watchNotes().test {
            assertEquals(exampleText, awaitItem().last().text)
            expectNoEvents()
        }
    }
}
