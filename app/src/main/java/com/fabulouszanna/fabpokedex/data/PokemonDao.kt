package com.fabulouszanna.fabpokedex.data

import androidx.room.Dao
import androidx.room.Query
import com.fabulouszanna.fabpokedex.data.pokemon.Pokemon
import kotlinx.coroutines.flow.Flow

@Dao
interface PokemonDao {
    @Query("SELECT * FROM pokemon")
    fun getAllPokemon(): Flow<List<Pokemon>>
}