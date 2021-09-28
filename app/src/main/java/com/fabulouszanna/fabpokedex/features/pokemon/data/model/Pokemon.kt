package com.fabulouszanna.fabpokedex.features.pokemon.data.model

import androidx.room.*
import com.fabulouszanna.fabpokedex.features.pokemon.domain.model.PokemonCard

@Entity(tableName = "pokemon")
data class Pokemon(
    @PrimaryKey
    @ColumnInfo(name = "pokemon_id")
    val pokemonId: String,
    val name: String,
    val types: List<String>,
    val species: String,
    val height: String,
    val weight: String,
    @ColumnInfo(name = "img_url")
    val imgUrl: String,
    @ColumnInfo(name = "hidden_ability")
    val hiddenAbility: String?,
    val abilities: List<String>,
    @ColumnInfo(name = "base_stats")
    val baseStats: BaseStats,
    @ColumnInfo(name = "pokedex_entry")
    val pokedexEntry: String,
    val evolutions: List<Evolution>,
    val moves: String?
//    val moves: List<Move>? = null
) {
    fun toPokemonCard(): PokemonCard {
        return PokemonCard(
            id = pokemonId,
            name = name,
            imgUrl = imgUrl,
            type = types[0]
        )
    }
}