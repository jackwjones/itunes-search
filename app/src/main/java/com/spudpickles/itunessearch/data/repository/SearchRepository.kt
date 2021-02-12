package com.spudpickles.itunessearch.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.spudpickles.itunessearch.data.entities.ITunesItem
import com.spudpickles.itunessearch.network.NetworkExecutor
import com.spudpickles.itunessearch.network.api.ITunesApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SearchRepository @Inject constructor(
    private val iTunesApiService: NetworkExecutor<ITunesApiService>
) {
    private val _searchResults = MutableLiveData<List<ITunesItem>>()
    val searchResults: LiveData<List<ITunesItem>> = _searchResults

    private val _error = MutableLiveData<Boolean>(false)
    val error: LiveData<Boolean> = _error

    suspend fun clearResults() {
        withContext(Dispatchers.IO) {
            _searchResults.postValue(emptyList())
        }
    }

    suspend fun clearError() {
        withContext(Dispatchers.IO) {
            _error.postValue(false)
        }
    }

    suspend fun searchItunes(searchTerm: String): Int {
        return withContext(Dispatchers.IO) {
            val response = iTunesApiService.execute { api ->
                api.getTitles(searchTerm)
            }

            if (response.isSuccess()) {
                val items = ArrayList<ITunesItem>()
                for (item in response.content?.results ?: emptyList()) {
                    if (!item.artistName.isNullOrBlank() && !item.trackName.isNullOrBlank()) {
                        items.add(
                            ITunesItem(
                                artistName = item.artistName,
                                trackName = item.trackName,
                                trackPrice = item.trackPrice ?: -1,
                                releaseDateString = item.releaseDate ?: "",
                                primaryGenreName = item.primaryGenreName ?: ""
                            )
                        )
                    }
                }
                _searchResults.postValue(items)
                items.count()
            } else {
                Timber.d("error getting search result")
                _searchResults.postValue(emptyList())
                _error.postValue(true)
                0
            }
        }
    }
}