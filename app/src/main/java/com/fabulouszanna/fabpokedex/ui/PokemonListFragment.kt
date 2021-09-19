package com.fabulouszanna.fabpokedex.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.fabulouszanna.fabpokedex.adapters.PokemonListAdapter
import com.fabulouszanna.fabpokedex.databinding.FragmentPokemonListBinding
import com.fabulouszanna.fabpokedex.other.RecyclerViewGridLayoutSpace
import com.fabulouszanna.fabpokedex.ui.viewmodels.PokemonViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class PokemonListFragment @Inject constructor(private val pokemonListAdapter: PokemonListAdapter) :
    Fragment() {
    private lateinit var binding: FragmentPokemonListBinding
    private val viewModel: PokemonViewModel by viewModels()
    private var statusBarDefaultColor: Int? = null
    private var isSearchCleared = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View =
        FragmentPokemonListBinding.inflate(inflater, container, false).also { binding = it }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        changeStatusBarColor()
        subscribeToObservers()
        setupRecyclerView()
        setupSearchView()
    }

    private fun changeStatusBarColor() {
        if (statusBarDefaultColor == null) {
            statusBarDefaultColor = requireActivity().window.statusBarColor
            return
        }
        requireActivity().window.apply {
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            statusBarColor = statusBarDefaultColor!!
        }
    }

    private fun subscribeToObservers() {
        viewModel.pokemonList.observe(viewLifecycleOwner) {
            pokemonListAdapter.pokemonListItems = it
            if (isSearchCleared) {
                lifecycleScope.launch {
                    delay(10)
                    binding.pokemonList.layoutManager?.scrollToPosition(0)
                }
            }
        }
    }

    private fun setupRecyclerView() {
        binding.pokemonList.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = pokemonListAdapter
            addItemDecoration(RecyclerViewGridLayoutSpace(includeEdge = true))
        }
        pokemonListAdapter.setOnCardClicked {
            findNavController().navigate(
                PokemonListFragmentDirections.actionPokemonListFragmentToPokemonDetailsFragment(
                    it
                )
            )
        }
    }

    private fun setupSearchView() {
        val pendingQuery = viewModel.filteredName.value
        binding.searchView.apply {
            maxWidth = Int.MAX_VALUE

            if (pendingQuery != null && pendingQuery.isNotEmpty()) {
                binding.textView.visibility = View.INVISIBLE
                setQuery(pendingQuery, false)
            }

            setOnSearchClickListener {
                binding.textView.visibility = View.INVISIBLE
            }
            setOnCloseListener {
                binding.textView.visibility = View.VISIBLE
                isSearchCleared = false
                false
            }

            setOnQueryTextListener(object :
                androidx.appcompat.widget.SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    viewModel.filteredName.postValue(newText ?: "")
                    return true
                }
            })

            findViewById<ImageView>(androidx.appcompat.R.id.search_close_btn).setOnClickListener {
                if (query.isEmpty()) {
                    isIconified = true
                } else {
                    setQuery("", false)
                    isSearchCleared = true
                }
            }
        }
    }
}