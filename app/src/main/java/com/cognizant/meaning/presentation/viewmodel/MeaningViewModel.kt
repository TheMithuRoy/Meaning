package com.cognizant.meaning.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cognizant.common.Resource
import com.cognizant.meaning.domain.usecase.GetMeaningsUseCase
import com.cognizant.meaning.presentation.MeaningUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MeaningViewModel @Inject constructor(
    private val getMeaningsUseCase: GetMeaningsUseCase
) : ViewModel() {

    private val _state = mutableStateOf(MeaningUiState(isInitialState = true))
    val state: State<MeaningUiState> = _state

    private val _sfQuery = mutableStateOf("")
    val sfQuery: State<String> = _sfQuery

    private var searchMeaningJob: Job? = null

    fun onQueryChange(sfQuery: String) {
        _sfQuery.value = sfQuery
        searchMeaningJob?.cancel()
        searchMeaningJob = getMeanings(sfQuery)
    }

    private fun getMeanings(sf: String) = viewModelScope.launch {
        delay(300L)
        getMeaningsUseCase(sf).onEach { resource ->
            when (resource) {
                is Resource.Loading -> {
                    _state.value = MeaningUiState(isLoading = true)
                }
                is Resource.Success -> {
                    val meanings = resource.data.orEmpty()
                    _state.value =
                        if(meanings.isEmpty() && sf.isBlank()) {
                            MeaningUiState(isInitialState = true)
                        } else if (meanings.isEmpty()) {
                            MeaningUiState(noDataAvailable = true)
                        } else {
                            MeaningUiState(meanings = meanings)
                        }
                }
                is Resource.Error -> {
                    _state.value = MeaningUiState(
                        error = resource.message.orEmpty()
                    )
                }
            }
        }.launchIn(this)
    }
}