package com.spudpickles.itunessearch.di

import com.spudpickles.itunessearch.ui.ResultsFragment
import com.spudpickles.itunessearch.ui.SearchFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class BindingModule {
    @ContributesAndroidInjector
    abstract fun searchFragment(): SearchFragment

    @ContributesAndroidInjector
    abstract fun resultsFragment(): ResultsFragment
}
