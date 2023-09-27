package com.yanneckreiss.adaptivescreensizeexample.ui.features.notes.overview

import com.yanneckreiss.adaptivescreensizeexample.domain.model.Note
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

data class NotesOverviewState(
    val notes: ImmutableList<Note> = persistentListOf(),
)