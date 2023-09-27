package com.yanneckreiss.adaptivescreensizeexample.data.entities

import java.time.ZonedDateTime
import java.util.UUID

data class NoteEntity(
    val uid: UUID,
    val text: String,
    val dateOfCreation: ZonedDateTime,
)
