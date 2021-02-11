package com.spudpickles.itunessearch.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.spudpickles.itunessearch.R
import com.spudpickles.itunessearch.databinding.FragmentResultsBinding
import com.spudpickles.itunessearch.di.ViewModelFactory
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class ResultsFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)
            .get(ResultsViewModel::class.java)
    }

    private lateinit var binding: FragmentResultsBinding
    private lateinit var resultsAdapter: ResultsAdapter

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
            R.layout.fragment_results, container, false
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
        viewModel.searchRepository.searchResults.observe(viewLifecycleOwner, Observer { results ->
            if (results.isNotEmpty()) {
                resultsAdapter = ResultsAdapter(requireContext(), results)
                binding.resultsRecycler.swapAdapter(resultsAdapter, true)

                viewModel.clearResults()
            }
        })
    }
}