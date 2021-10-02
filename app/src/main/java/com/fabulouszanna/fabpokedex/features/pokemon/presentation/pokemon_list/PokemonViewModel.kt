package com.fabulouszanna.fabpokedex.features.pokemon.presentation.pokemon_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fabulouszanna.fabpokedex.features.pokemon.domain.use_cases.PokemonUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class PokemonViewModel @Inject constructor(private val useCases: PokemonUseCases) :
    ViewModel() {
    private val _pokemonListState = MutableLiveData(PokemonListState())
    val pokemonListState: LiveData<PokemonListState> = _pokemonListState

    private var getPokemonListJob: Job? = null

    init {
        getPokemonList(
            filteredName = _pokemonListState.value!!.filteredName,
            filteredType = _pokemonListState.value!!.filteredType
        )
    }

    fun onEvent(event: PokemonListEvent) {
        when (event) {
            is PokemonListEvent.FilterByName -> {
                filterListByName(event.name)
            }
            is PokemonListEvent.FilterByType -> {
                filterListByType(event.type)
            }
        }
    }


    private fun filterListByName(name: String) {
        _pokemonListState.value = pokemonListState.value!!.copy(
            filteredName = name
        )
        getPokemonList(
            filteredName = name,
            filteredType = _pokemonListState.value!!.filteredType
        )
    }

    private fun filterListByType(type: String) {
        _pokemonListState.value = pokemonListState.value!!.copy(
            filteredType = type
        )
        getPokemonList(
            filteredName = _pokemonListState.value!!.filteredName,
            filteredType = type
        )
    }

    private fun getPokemonList(filteredName: String, filteredType: String) {
        getPokemonListJob?.cancel()
        getPokemonListJob =
            useCases.getPokemonListUseCase.execute(filteredName, filteredType).onEach {
                _pokemonListState.value = _pokemonListState.value!!.copy(
                    pokemonList = it,
                    isListEmpty = it.isEmpty()
                )
            }.launchIn(viewModelScope)
    }
}