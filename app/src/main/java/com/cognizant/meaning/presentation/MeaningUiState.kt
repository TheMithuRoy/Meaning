package com.cognizant.meaning.presentation

import com.cognizant.meaning.domain.model.Meaning

data class MeaningUiState(
    val isLoading: Boolean = false,
    val meanings: List<Meaning> = emptyList(),
    val error: String = "",
    val noDataAvailable: Boolean = false,
    val isInitialState: Boolean = false
)
