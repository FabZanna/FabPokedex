package com.fabulouszanna.fabpokedex.features.pokemon.presentation.pokemon_details

import androidx.lifecycle.*
import com.fabulouszanna.fabpokedex.features.pokemon.data.model.Pokemon
import com.fabulouszanna.fabpokedex.features.pokemon.domain.use_cases.PokemonUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PokemonDetailsViewModel @Inject constructor(private val useCases: PokemonUseCases) :
    ViewModel() {
    private val _currentId = MutableLiveData<String>()
    val pokemon: LiveData<Pokemon> = Transformations.switchMap(_currentId) { id ->
        useCases.getPokemonUseCase.execute(id).asLiveData()
    }

    fun updateId(id: String) {
        _currentId.value = id
    }
}