package com.fabulouszanna.fabpokedex.features.pokemon.data.model

import com.google.gson.annotations.SerializedName

data class Move(
    @SerializedName("level_up")
    val levelUp: List<LevelUp>,
    @SerializedName("egg_move")
    val eggMove: List<EggMove>? = null,
    val tutor: List<Tutor>? = null,
    val TM: List<TM>? = null,
    val TR: List<TR>? = null
)