package com.fabulouszanna.fabpokedex.data.pokemon

import androidx.room.*

@Entity(tableName = "pokemon")
data class Pokemon(
    @PrimaryKey
    val id: Int,
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
//    val moves: List<Move>?
)