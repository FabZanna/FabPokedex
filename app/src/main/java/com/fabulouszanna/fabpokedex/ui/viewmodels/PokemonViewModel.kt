package com.fabulouszanna.fabpokedex.ui.viewmodels

import androidx.lifecycle.*
import com.fabulouszanna.fabpokedex.data.pokemon.SchematicPokemon
import com.fabulouszanna.fabpokedex.repositories.PokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PokemonViewModel @Inject constructor(private val repository: PokemonRepository) :
    ViewModel() {

    val filteredName = MutableLiveData<String>("")
    val pokemonList: LiveData<List<SchematicPokemon>> =
        Transformations.switchMap(filteredName) { name ->
            repository.getPokemonList(name).asLiveData()
        }

    fun getPokemonById(id: Int) = repository.getPokemonById(id).asLiveData()
}