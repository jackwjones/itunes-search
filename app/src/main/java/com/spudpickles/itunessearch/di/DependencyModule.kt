package com.spudpickles.itunessearch.di

import androidx.lifecycle.ViewModel
import com.spudpickles.itunessearch.data.repository.SearchRepository
import com.spudpickles.itunessearch.ui.SearchViewModel
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
class DependencyModule {
    @Provides
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    fun getSearchViewModel(
        searchRepository: SearchRepository
    ): ViewModel =
        SearchViewModel(searchRepository)
}
