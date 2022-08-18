package com.cognizant.meaning.presentation.ui.composable

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.cognizant.meaning.presentation.viewmodel.MeaningViewModel

@Composable
fun MeaningSearchScreen(
    viewModel: MeaningViewModel = hiltViewModel()
) {
    val sfQuery = viewModel.sfQuery.value
    val uiState = viewModel.state.value

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Column {
            SearchBar(
                value = sfQuery,
                onValueChange = viewModel::onQueryChange
            )
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(uiState.meanings) { meaning ->
                    MeaningItem(meaning = meaning)
                }
            }
        }
    }
}

@Preview
@Composable
fun MeaningSearchScreenPreview() {
    MeaningSearchScreen()
}