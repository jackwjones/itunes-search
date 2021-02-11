package com.spudpickles.itunessearch.ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.spudpickles.itunessearch.R
import com.spudpickles.itunessearch.databinding.FragmentSearchBinding
import com.spudpickles.itunessearch.di.ViewModelFactory
import dagger.android.support.AndroidSupportInjection
import timber.log.Timber
import javax.inject.Inject

class SearchFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)
            .get(SearchViewModel::class.java)
    }

    private lateinit var binding: FragmentSearchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidSupportInjection.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_search, container, false
        )
        binding.lifecycleOwner = viewLifecycleOwner
        binding.fragment = this
        binding.viewModel = viewModel

        setupViews()
        observe()

        return binding.root
    }

    fun setupViews() {
    }

    fun observe() {
        viewModel.searchRepository.searchResults.observe(viewLifecycleOwner, Observer { items ->
            items.forEach {
                Timber.d("item: ${it.trackName}")
            }
        })
    }

    fun clicked(view: View) {
        viewModel.fetchSearchResults("tori amos")
    }
}
