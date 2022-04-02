package com.lillydoo.jokes

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.lillydoo.jokes.data.model.Jokes
import com.lillydoo.jokes.databinding.FragmentJokesListBinding
import com.lillydoo.jokes.viewmodel.JokesListViewModel
import com.lillydoo.shared.result.DataState
import com.lillydoo.shared.utils.Constants.TAG
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class JokesListFragment : Fragment() {

    private val viewModel: JokesListViewModel by viewModels()
    private lateinit var binding: FragmentJokesListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentJokesListBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewModel()
        observeUiState()
        handleRefresh()
    }

    private fun handleRefresh() {
        binding.swiperefresh.setOnRefreshListener {
            Log.i(TAG, "onRefresh called from SwipeRefreshLayout")
            binding.jokesList.isVisible = false
            viewModel.getJokesList()
        }
    }

    private fun setupViewModel() {
        viewModel.getJokesList()
    }

    private fun observeUiState() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.viewState.collect {
                when (it) {
                    is DataState.Success -> {
                        binding.loader.isVisible = false
                        binding.loader.stopShimmer()
                        binding.swiperefresh.isRefreshing = false
                        binding.jokesList.isVisible = true
                        setupAdapterUI(it.data)
                    }
                    is DataState.Loading -> {
                        binding.loader.isVisible = true
                        binding.loader.startShimmer()
                    }
                    is DataState.Error -> {
                        Log.e(TAG, "Error retrieving online records", it.exception)
                        binding.loader.isVisible = false
                        binding.loader.stopShimmer()
                        binding.swiperefresh.isRefreshing = false
                        Snackbar.make(
                            binding.root,
                            it.exception.localizedMessage ?: getString(R.string.generic_error),
                            Snackbar.LENGTH_LONG
                        ).show()
                    }
                }
            }
        }
    }


    private fun setupAdapterUI(jokesList: List<Jokes>) {
        with(binding.jokesList) {
            adapter = JokesListAdapter(jokesList)
        }
    }
}