package com.fabulouszanna.fabpokedex.features.pokemon.presentation.pokemon_list

import androidx.lifecycle.*
import com.fabulouszanna.fabpokedex.features.pokemon.domain.model.PokemonCard
import com.fabulouszanna.fabpokedex.features.pokemon.domain.repository.PokemonRepository
import com.fabulouszanna.fabpokedex.features.pokemon.domain.usecases.PokemonUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class PokemonViewModel @Inject constructor(private val useCases: PokemonUseCases) :
    ViewModel() {
    val filteredName = MutableLiveData("")
    val pokemonList: LiveData<List<PokemonCard>> = useCases.getPokemonListUseCase.execute().map { list ->
        list.map {
            it.toPokemonCard()
        }
    }.asLiveData()

//    val pokemonList: LiveData<List<PokemonCard>> =
//        Transformations.switchMap(filteredName) { name ->
//            repository.getAllPokemon().map { list ->
//                list.filter { pokemon ->
//                    pokemon.name.lowercase().contains(name.lowercase())
//                }
//            }.asLiveData()
//        }
}