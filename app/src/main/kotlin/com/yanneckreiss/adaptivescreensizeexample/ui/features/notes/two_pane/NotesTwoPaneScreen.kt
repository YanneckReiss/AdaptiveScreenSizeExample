package com.yanneckreiss.adaptivescreensizeexample.ui.features.notes.two_pane

import android.app.Activity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.accompanist.adaptive.FoldAwareConfiguration
import com.google.accompanist.adaptive.HorizontalTwoPaneStrategy
import com.google.accompanist.adaptive.TwoPane
import com.google.accompanist.adaptive.calculateDisplayFeatures
import com.yanneckreiss.adaptivescreensizeexample.ui.features.notes.details.NotesDetailsScreen
import com.yanneckreiss.adaptivescreensizeexample.ui.features.notes.overview.NotesOverviewScreen
import org.koin.androidx.compose.koinViewModel
import java.util.UUID

@Composable
fun NotesTwoPaneScreen(
    viewModel: NotesTwoPaneViewModel = koinViewModel(),
) {

    val state: NotesTwoPaneState by viewModel.state.collectAsStateWithLifecycle()

    NotesTwoPaneContent(
        selectedNoteUid = state.selectedNoteUid,
        onNoteUidSelected = viewModel::selectNoteUid,
    )
}

@Composable
private fun NotesTwoPaneContent(
    selectedNoteUid: UUID?,
    onNoteUidSelected: (UUID) -> Unit,
) {

    val activity = LocalContext.current as Activity

    TwoPane(
        modifier = Modifier.fillMaxSize(),
        first = {
            NotesOverviewScreen(
                onShowNoteDetails = onNoteUidSelected
            )
        },
        second = {
            NotesDetailsScreen(
                noteUid = selectedNoteUid,
            )
        },
        strategy = HorizontalTwoPaneStrategy(splitFraction = 0.5f),
        displayFeatures = calculateDisplayFeatures(activity),
        foldAwareConfiguration = FoldAwareConfiguration.HorizontalFoldsOnly
    )
}
