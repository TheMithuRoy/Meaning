package com.cognizant.meaning.ui.composable

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun MeaningSearchScreen() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Column {
            SearchBar()
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                item { MeaningItem(meaning = dummyMeaning) }
                item { MeaningItem(meaning = dummyMeaning) }
                item { MeaningItem(meaning = dummyMeaning) }
                item { MeaningItem(meaning = dummyMeaning) }
            }
        }
    }
}

@Preview
@Composable
fun MeaningSearchScreenPreview() {
    MeaningSearchScreen()
}