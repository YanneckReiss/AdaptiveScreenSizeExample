@file:OptIn(ExperimentalMaterial3Api::class)

package com.yanneckreiss.adaptivescreensizeexample.ui.features.notes.details

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.yanneckreiss.adaptivescreensizeexample.core.toLocalizedFormat
import com.yanneckreiss.adaptivescreensizeexample.domain.model.Note
import com.yanneckreiss.adaptivescreensizeexample.ui.base.navigation.graphs.NotesNavGraph
import com.yanneckreiss.adaptivescreensizeexample.ui.theme.FoldableExampleTheme
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf
import java.time.ZonedDateTime
import java.util.UUID

@NotesNavGraph
@Destination(navArgsDelegate = NoteDetailsNavArgs::class)
@Composable
fun NotesDetailsNavigation(
    noteDetailsNavArgs: NoteDetailsNavArgs,
    navigator: DestinationsNavigator,
) {

    NotesDetailsScreen(
        noteUid = noteDetailsNavArgs.notesUid,
        onNavBack = {
            navigator.popBackStack()
        }
    )
}

@Composable
fun NotesDetailsScreen(
    noteUid: UUID? = null,
    onNavBack: (() -> Unit)? = null,
    viewModel: NoteDetailsViewModel = koinViewModel(
        key = noteUid.toString(),
        parameters = { parametersOf(noteUid) }
    ),
) {
    val state: NoteDetailsState by viewModel.state.collectAsStateWithLifecycle()

    NotesDetailsContent(
        note = state.note,
        onNavBack = onNavBack,
    )
}

@Composable
private fun NotesDetailsContent(
    note: Note?,
    onNavBack: (() -> Unit)?,
) {
    Scaffold(
        modifier = Modifier.fillMaxWidth(),
        topBar = {
            if (onNavBack != null) {
                TopAppBar(
                    title = { Text("Notice details") },
                    navigationIcon = {
                        IconButton(onClick = onNavBack) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = "Back"
                            )
                        }
                    },
                )
            }
        }
    ) { innerPadding ->
        if (note != null) {
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(16.dp)
            ) {
                val formattedDate: String = remember(note.dateOfCreation) {
                    note.dateOfCreation.toLocalizedFormat()
                }
                Text(
                    text = formattedDate,
                    style = MaterialTheme.typography.displaySmall
                )
                Spacer(
                    modifier = Modifier.padding(8.dp)
                )
                Text(
                    modifier = Modifier.weight(1f),
                    text = note.text,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        } else {
            Box(
                modifier = Modifier.padding(innerPadding)
            ) {
                Text(
                    modifier = Modifier.padding(16.dp),
                    text = "No note information found"
                )
            }
        }
    }
}

@Composable
@Preview
private fun NotesDetailsPreview() {
    FoldableExampleTheme {
        val exampleNote: Note = remember {
            Note(
                uid = UUID.randomUUID(),
                text = "Example note",
                dateOfCreation = ZonedDateTime.now(),
            )
        }

        NotesDetailsContent(
            note = exampleNote,
            onNavBack = {}
        )
    }
}
