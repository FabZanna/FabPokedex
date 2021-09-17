package com.fabulouszanna.fabpokedex.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.fabulouszanna.fabpokedex.R
import com.fabulouszanna.fabpokedex.adapters.PokemonListAdapter
import com.fabulouszanna.fabpokedex.databinding.FragmentPokemonListBinding
import com.fabulouszanna.fabpokedex.other.RecyclerViewGridLayoutSpace
import com.fabulouszanna.fabpokedex.ui.viewmodels.PokemonViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PokemonListFragment @Inject constructor(private val pokemonListAdapter: PokemonListAdapter) :
    Fragment() {
    private lateinit var binding: FragmentPokemonListBinding
    private val viewModel: PokemonViewModel by viewModels()
    private var statusBarDefaultColor: Int? = null

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
}