package com.fabulouszanna.fabpokedex.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.fabulouszanna.fabpokedex.data.pokemon.SchematicPokemon
import com.fabulouszanna.fabpokedex.repositories.PokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PokemonViewModel @Inject constructor(private val repository: PokemonRepository) :
    ViewModel() {
    val pokemonList: LiveData<List<SchematicPokemon>> = repository.getAllPokemon().asLiveData()
}