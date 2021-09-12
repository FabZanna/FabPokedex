package com.fabulouszanna.fabpokedex.data.pokemon

data class Move(
    val level_up: List<LevelUp>,
    val egg_move: List<EggMove>?,
    val tutor: List<Tutor>?,
    val TM: List<TM>?,
    val TR: List<TR>?
)