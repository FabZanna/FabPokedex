package com.fabulouszanna.fabpokedex.features.pokemon.domain.use_cases

import com.fabulouszanna.fabpokedex.features.pokemon.data.repository.FakePokemonRepository
import com.fabulouszanna.fabpokedex.features.pokemon.domain.repository.PokemonRepository
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetPokemonListUseCaseTest {
    private lateinit var getPokemonListUseCase: GetPokemonListUseCase
    private lateinit var fakeRepository: PokemonRepository

    @Before
    fun setUp() {
        fakeRepository = FakePokemonRepository()
        getPokemonListUseCase = GetPokemonListUseCase(fakeRepository)
    }

    @Test
    fun `Filter list by name, correct filtering`(): Unit = runBlocking {
        val pokemonList = getPokemonListUseCase.execute(filteredName = "bu").first()
        assertThat(pokemonList).isNotEmpty()
        assertThat(pokemonList.size).isEqualTo(1)
        assertThat(pokemonList[0].name).isEqualTo("Bulbasaur")
    }

    @Test
    fun `Filter list by name, no results`(): Unit = runBlocking {
        val pokemonList = getPokemonListUseCase.execute(filteredName = "qz").first()
        assertThat(pokemonList).isEmpty()
    }

    @Test
    fun `Filter list by type, correct filtering`(): Unit = runBlocking {
        val pokemonList = getPokemonListUseCase.execute(filteredType = "Poison").first()
        assertThat(pokemonList).isNotEmpty()
        assertThat(pokemonList.size).isEqualTo(2)
        assertThat(pokemonList[0].name).isEqualTo("Bulbasaur")
        assertThat(pokemonList[1].name).isEqualTo("Ivysaur")
    }

    @Test
    fun `Filter list by name and type, correct filtering`(): Unit = runBlocking {
        val pokemonList = getPokemonListUseCase.execute(filteredName = "bu", filteredType = "Poison").first()
        assertThat(pokemonList).isNotEmpty()
        assertThat(pokemonList.size).isEqualTo(1)
        assertThat(pokemonList[0].name).isEqualTo("Bulbasaur")
    }
}