package com.spudpickles.itunessearch.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.spudpickles.itunessearch.data.repository.SearchRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class ResultsViewModel @Inject constructor(
    val searchRepository: SearchRepository
) : ViewModel() {

    fun clearResults() {
        viewModelScope.launch {
            searchRepository.clearResults()
        }
    }
}
