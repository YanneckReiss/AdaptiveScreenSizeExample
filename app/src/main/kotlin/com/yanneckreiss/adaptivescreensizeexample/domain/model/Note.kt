package com.yanneckreiss.adaptivescreensizeexample.domain.model

import java.time.ZonedDateTime
import java.util.UUID

data class Note(
    val uid: UUID,
    val text: String,
    val dateOfCreation: ZonedDateTime,
)
