@file:OptIn(
    ExperimentalMaterial3Api::class
)

package com.yanneckreiss.adaptivescreensizeexample.ui.features.notes.create

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.yanneckreiss.adaptivescreensizeexample.ui.base.navigation.graphs.NotesNavGraph
import com.yanneckreiss.adaptivescreensizeexample.ui.base.navigation.styles.FullWidthDialog
import org.koin.androidx.compose.koinViewModel

@NotesNavGraph
@Destination(style = FullWidthDialog::class)
@Composable
fun CreateNoteNavigation(
    navigator: DestinationsNavigator,
) {

    CreateNoteDialog(
        onNavBack = { navigator.popBackStack() }
    )
}

@Composable
fun CreateNoteDialog(
    onNavBack: () -> Unit,
    viewModel: CreateNoteViewModel = koinViewModel(),
) {

    val state: CreateNoteState by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = state.noteCreated) {
        if (state.noteCreated) {
            onNavBack()
        }
    }

    CreateNoteContent(
        onCloseDialog = onNavBack,
        createNote = viewModel::createNote,
        onTextChange = viewModel::onTextChange,
        currentText = state.currentText
    )
}

@Composable
private fun CreateNoteContent(
    onCloseDialog: () -> Unit,
    createNote: () -> Unit,
    onTextChange: (String) -> Unit,
    currentText: String,
) {

    Card(
        modifier = Modifier.padding(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 16.dp)
    ) {
        Scaffold(
            modifier = Modifier.fillMaxHeight(fraction = 0.6f),
            topBar = {
                TopAppBar(
                    title = { Text("Add note") },
                    actions = {
                        IconButton(onClick = onCloseDialog) {
                            Icon(imageVector = Icons.Default.Close, contentDescription = "Close")
                        }
                    },
                )
            }
        ) { innerPadding ->

            Column(
                modifier = Modifier
                    .padding(innerPadding),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .padding(16.dp),
                    value = currentText,
                    onValueChange = onTextChange
                )

                Button(onClick = createNote) {
                    Text(text = "Create note")
                }
            }
        }
    }
}
