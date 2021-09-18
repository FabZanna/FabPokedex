package com.fabulouszanna.fabpokedex.ui.viewmodels

import androidx.lifecycle.*
import com.fabulouszanna.fabpokedex.data.pokemon.SchematicPokemon
import com.fabulouszanna.fabpokedex.repositories.PokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

//data class PokemonViewState(
//    val pokemon: List<SchematicPokemon> = listOf(),
//    val name: String = ""
//)

@HiltViewModel
class PokemonViewModel @Inject constructor(private val repository: PokemonRepository) :
    ViewModel() {

    //    private val _pokemonList = MediatorLiveData<PokemonViewState>()
    val filteredName = MutableLiveData<String>("")
    val pokemonList: LiveData<List<SchematicPokemon>> =
        Transformations.switchMap(filteredName) { name ->
            repository.getPokemonList(name).asLiveData()
        }

//    private val lastSource: LiveData<PokemonViewState>? = null
//    val pokemonList: LiveData<List<SchematicPokemon>> = repository.getAllPokemon().asLiveData()

//    init {
//        filterPokemonList("")
//    }

//    fun filterPokemonList(name: String) {
//        lastSource?.let { _pokemonList.removeSource(it) }
//        val items = repository.getPokemonList(name).map { PokemonViewState(it, name) }.asLiveData()
//        _pokemonList.addSource(items) { viewState ->
//            _pokemonList.value = viewState
//        }
//    }

    fun getPokemonById(id: Int) = repository.getPokemonById(id).asLiveData()
}