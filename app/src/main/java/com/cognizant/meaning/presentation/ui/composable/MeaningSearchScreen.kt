package com.cognizant.meaning.presentation.ui.composable

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.cognizant.meaning.R
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
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            SearchBar(
                value = sfQuery,
                onValueChange = viewModel::onQueryChange
            )
            if (uiState.isLoading) {
                CircularProgressIndicator()
            } else if (uiState.noDataAvailable) {
                NoData(message = "No Data Found", imageId = R.drawable.ic_no_data)
            } else if (uiState.error.isNotBlank()) {
                NoData(message = uiState.error, imageId = R.drawable.ic_error)
            } else {
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
}

@Composable
private fun NoData(
    message: String,
    @DrawableRes imageId: Int,
    modifier: Modifier = Modifier
) {
    Spacer(modifier = Modifier.size(8.dp))
    Image(
        painter = painterResource(id = imageId),
        contentDescription = null,
        modifier = modifier
            .scale(0.5f)
            .wrapContentSize()
    )
    Spacer(modifier = Modifier.size(8.dp))
    Text(text = message, fontWeight = FontWeight.SemiBold)
}

@Preview
@Composable
fun MeaningSearchScreenPreview() {
    MeaningSearchScreen()
}