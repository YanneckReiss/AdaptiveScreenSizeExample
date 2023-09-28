package com.yanneckreiss.adaptivescreensizeexample.data.repositories

import com.yanneckreiss.adaptivescreensizeexample.data.entities.NoteEntity
import com.yanneckreiss.adaptivescreensizeexample.data.mapper.toDomainModel
import com.yanneckreiss.adaptivescreensizeexample.domain.model.Note
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.withContext
import org.koin.core.annotation.Single
import java.time.ZonedDateTime
import java.util.UUID

/**
 * This class is a mock repository for notes. The dispatcher is not actually needed here, but it is
 * used to show how to test coroutines.
 */
@Single
class NotesRepository(
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
) {

    private val _mockedNotes = MutableStateFlow(listOf<NoteEntity>())

    init {
        loadMockedNotes()
    }

    fun watchNotes(): Flow<List<Note>> = _mockedNotes.map { notes ->
        notes.map { noteEntity -> noteEntity.toDomainModel() }
    }

    suspend fun addNote(
        text: String,
        timeOfCreation: ZonedDateTime,
    ) {
        withContext(dispatcher) {
            val newNote = NoteEntity(
                uid = UUID.randomUUID(),
                text = text,
                dateOfCreation = timeOfCreation,
            )

            _mockedNotes.update { notes ->
                notes + newNote
            }
        }
    }

    suspend fun deleteNote(uid: UUID) {
        withContext(dispatcher) {
            _mockedNotes.update {
                _mockedNotes.value.filter { noteEntity ->
                    noteEntity.uid != uid
                }
            }
        }
    }

    suspend fun getAll(): List<Note> = withContext(dispatcher) {
        withContext(dispatcher) {
            _mockedNotes.value.map(NoteEntity::toDomainModel)
        }
    }

    private fun loadMockedNotes() {
        val bookNotes: MutableList<NoteEntity> = mutableListOf<NoteEntity>()
        var baseDate: ZonedDateTime = ZonedDateTime.now()

        val bookComments: List<String> = listOf(
            "Just finished reading 'To Kill a Mockingbird'. It's a compelling narrative about racial injustice and moral growth.",
            "I'm halfway through 'The Great Gatsby'. The depiction of the Jazz Age is simply mesmerizing.",
            "Started '1984' by George Orwell. The dystopian world feels so real and thought-provoking.",
            "'Pride and Prejudice' is an insightful exploration of societal norms and personal integrity.",
            "'Moby Dick' was a bit lengthy, but the tale of obsession and revenge is unparalleled.",
            "'The Catcher in the Rye' delves deep into teenage angst and isolation. A moving read.",
            "The fantasy world in 'Lord of the Rings' is so vast and intricately detailed. Tolkien is a genius.",
            "'The Odyssey' is an epic journey filled with adventures and mythical creatures.",
            "Reading 'Brave New World'. The futuristic society is both fascinating and terrifying.",
            "'Animal Farm' is a brilliant satire on the corrupting influence of power.",
            "Thoroughly enjoyed 'The Picture of Dorian Gray'. The themes of vanity and moral duplicity are striking.",
            "Dove into 'Fahrenheit 451' last night. The fear of a bookless society is truly haunting.",
            "I found 'Jane Eyre' to be an evocative tale of love, morality, and self-respect.",
            "'The Grapes of Wrath' is a powerful reflection on the harsh realities of the Great Depression.",
            "Currently reading 'Dracula'. Stoker's portrayal of the vampire myth is captivating."
        )

        for (index: Int in 1..15) {
            val bookNoteText: String = bookComments[index - 1]
            bookNotes.add(
                NoteEntity(
                    uid = UUID.randomUUID(),
                    text = bookNoteText,
                    dateOfCreation = baseDate,
                )
            )
            baseDate = baseDate.minusDays((1..48).random().toLong())
        }

        _mockedNotes.value = bookNotes
    }
}
