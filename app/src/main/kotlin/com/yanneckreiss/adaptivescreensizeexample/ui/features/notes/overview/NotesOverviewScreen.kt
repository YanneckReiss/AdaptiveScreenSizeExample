@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)

package com.yanneckreiss.adaptivescreensizeexample.ui.features.notes.overview

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.yanneckreiss.adaptivescreensizeexample.domain.model.Note
import com.yanneckreiss.adaptivescreensizeexample.ui.base.navigation.graphs.NotesNavGraph
import com.yanneckreiss.adaptivescreensizeexample.ui.features.destinations.CreateNoteNavigationDestination
import com.yanneckreiss.adaptivescreensizeexample.ui.features.destinations.NotesDetailsNavigationDestination
import kotlinx.collections.immutable.ImmutableList
import org.koin.androidx.compose.koinViewModel
import java.util.UUID

@NotesNavGraph(start = true)
@Destination
@Composable
fun NotesOverviewNavigation(
    navigator: DestinationsNavigator,
) {

    NotesOverviewScreen(
        onShowNoteDetails = { noteUid: UUID -> navigator.navigate(NotesDetailsNavigationDestination(noteUid)) },
        onShowCreateNote = { navigator.navigate(CreateNoteNavigationDestination()) }
    )
}

@Composable
fun NotesOverviewScreen(
    onShowNoteDetails: (UUID) -> Unit,
    viewModel: NoteOverviewViewModel = koinViewModel(),
    onShowCreateNote: (() -> Unit)? = null,
) {

    val state: NotesOverviewState by viewModel.state.collectAsStateWithLifecycle()

    NotesOverviewContent(
        notes = state.notes,
        onShowNoteDetails = onShowNoteDetails,
        onShowCreateNote = onShowCreateNote
    )
}

@Composable
private fun NotesOverviewContent(
    notes: ImmutableList<Note>,
    onShowNoteDetails: (UUID) -> Unit,
    onShowCreateNote: (() -> Unit)?,
) {

    Scaffold(
        topBar = { TopAppBar(title = { Text("Notes") }) },
        floatingActionButton = {
            if (onShowCreateNote != null) {
                FloatingActionButton(
                    onClick = onShowCreateNote
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Add note"
                    )
                }
            }
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .padding(innerPadding),

            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            item(key = "TopSpacer") {
                Spacer(modifier = Modifier.padding(8.dp))
            }

            items(
                items = notes,
                key = Note::uid,
                contentType = { Note::class }
            ) { note: Note ->
                NoteItem(
                    modifier = Modifier
                        .fillMaxWidth()
                        .animateItemPlacement(),
                    note = note,
                    onClick = onShowNoteDetails
                )
            }
        }
    }
}

@Composable
private fun NoteItem(
    note: Note,
    onClick: (UUID) -> Unit,
    modifier: Modifier = Modifier,
) {

    Card(
        modifier = modifier,
        onClick = { onClick(note.uid) }
    ) {
        Text(
            modifier = Modifier.padding(16.dp),
            text = note.text,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
    }
}
