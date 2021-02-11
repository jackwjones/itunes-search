package com.spudpickles.itunessearch.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.spudpickles.itunessearch.data.entities.ITunesItem
import com.spudpickles.itunessearch.data.repository.SearchRepository
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class SearchViewModel @Inject constructor(
    val searchRepository: SearchRepository
) : ViewModel() {

    fun fetchSearchResults(searchTerm: String) {
        viewModelScope.launch {
            val count = searchRepository.searchItunes(searchTerm)
            Timber.d("viewModel found $count results")
        }
    }
}
