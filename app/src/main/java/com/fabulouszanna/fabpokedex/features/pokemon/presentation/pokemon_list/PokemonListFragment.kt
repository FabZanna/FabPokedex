package com.fabulouszanna.fabpokedex.features.pokemon.presentation.pokemon_list

import android.os.Bundle
import android.util.Log
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
import com.bumptech.glide.RequestManager
import com.fabulouszanna.fabpokedex.core.util.RecyclerViewGridLayoutSpace
import com.fabulouszanna.fabpokedex.core.util.hide
import com.fabulouszanna.fabpokedex.core.util.makeInvisible
import com.fabulouszanna.fabpokedex.core.util.show
import com.fabulouszanna.fabpokedex.databinding.FragmentPokemonListBinding
import com.fabulouszanna.fabpokedex.features.pokemon.presentation.pokemon_list.filter_bottom_sheet.TypeFilterBottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class PokemonListFragment : Fragment() {
    private lateinit var binding: FragmentPokemonListBinding
    private val viewModel: PokemonViewModel by viewModels()
    private var statusBarDefaultColor: Int? = null
    private lateinit var pokemonListAdapter: PokemonListAdapter
    private var isSearchCleared = false
    private var isNavigating = false

    @Inject
    lateinit var glide: RequestManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPokemonListBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun initializeSearch() {
        isSearchCleared = false
        isNavigating = false
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeSearch()
        pokemonListAdapter = PokemonListAdapter(glide)
        changeStatusBarColor()
        subscribeToObservers()
        setupRecyclerView()
        setupSearchView()
        setupFiltering()
        setupNavigation()
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
        viewModel.pokemonListState.observe(viewLifecycleOwner) { state ->
            if (state.isListEmpty) {
                binding.emptyList.show()
                binding.pokemonList.hide()
            } else {
                binding.emptyList.hide()
                binding.pokemonList.show()
            }
            pokemonListAdapter.pokemonListItems = state.pokemonList
            if (!isNavigating && isSearchCleared) {
                lifecycleScope.launch {
                    delay(10)
                    binding.pokemonList.layoutManager?.scrollToPosition(0)
                }
            }
        }
    }

    private fun setupNavigation() {
        pokemonListAdapter.setOnCardClicked { id ->
            isNavigating = true
            val action =
                PokemonListFragmentDirections.actionPokemonListFragmentToPokemonDetailsFragment(id)
            findNavController().navigate(action)
        }
    }

    private fun setupRecyclerView() {
        binding.pokemonList.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = pokemonListAdapter
            addItemDecoration(RecyclerViewGridLayoutSpace(includeEdge = true))
        }
    }

    private fun setupFiltering() {
        binding.filter.setOnClickListener {
            val typesBottomSheet = TypeFilterBottomSheetDialogFragment {
                viewModel.onEvent(PokemonListEvent.FilterByType(it))
            }
            typesBottomSheet.show(requireActivity().supportFragmentManager, null)
        }
    }

    private fun setupSearchView() {
        val pendingQuery = viewModel.pokemonListState.value?.filteredName
        binding.searchView.apply {
//            maxWidth = Int.MAX_VALUE

            if (pendingQuery != null && pendingQuery.isNotEmpty()) {
                binding.textView.makeInvisible()
                setQuery(pendingQuery, false)
            }

            setOnSearchClickListener {
                binding.textView.makeInvisible()
            }
            setOnCloseListener {
                binding.textView.show()
//                viewModel.onEvent(PokemonListEvent.FilterByName(""))
//                scrollToTop()
                false
            }

            setOnQueryTextListener(object :
                androidx.appcompat.widget.SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    if (newText.isNullOrEmpty()) {
                        isSearchCleared = true
                    }
                    viewModel.onEvent(PokemonListEvent.FilterByName(newText ?: ""))
                    return true
                }
            })

            findViewById<ImageView>(androidx.appcompat.R.id.search_close_btn).setOnClickListener {
                if (query.isEmpty()) {
                    isIconified = true
                } else {
                    setQuery("", true)
//                    viewModel.onEvent(PokemonListEvent.FilterByName(""))
                    isSearchCleared = true
                }
            }
        }
    }
}