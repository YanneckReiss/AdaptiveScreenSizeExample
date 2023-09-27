package com.yanneckreiss.adaptivescreensizeexample.data.mapper

import com.yanneckreiss.adaptivescreensizeexample.data.entities.NoteEntity
import com.yanneckreiss.adaptivescreensizeexample.domain.model.Note

fun NoteEntity.toDomainModel(): Note {
    return Note(
        uid = uid,
        text = text,
        dateOfCreation = dateOfCreation,
    )
}

fun Note.toEntity(): NoteEntity {
    return NoteEntity(
        uid = uid,
        text = text,
        dateOfCreation = dateOfCreation,
    )
}
