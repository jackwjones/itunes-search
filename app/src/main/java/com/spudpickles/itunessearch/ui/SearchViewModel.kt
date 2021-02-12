package com.spudpickles.itunessearch.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.spudpickles.itunessearch.data.repository.SearchRepository
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class SearchViewModel @Inject constructor(
    val searchRepository: SearchRepository
) : ViewModel() {

    private val _spin = MutableLiveData<Boolean>(false)
    val spin: LiveData<Boolean> = _spin

    fun fetchSearchResults(searchTerm: String) {
        viewModelScope.launch {
            _spin.value = true
            searchRepository.clearError()
            val count = searchRepository.searchItunes(searchTerm)
            _spin.value = false
            Timber.d("viewModel found $count results")
        }
    }
}
