package com.fabulouszanna.fabpokedex.features.pokemon.data.db

import androidx.room.TypeConverter
import com.fabulouszanna.fabpokedex.features.pokemon.data.model.BaseStats
import com.fabulouszanna.fabpokedex.features.pokemon.data.model.Evolution
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class Converters {
    @TypeConverter
    fun toListOfStrings(value: String): List<String> {
        val objectType: Type = object : TypeToken<List<String>>() {}.type
        return Gson().fromJson(value, objectType)
    }

    @TypeConverter
    fun fromListOfStrings(value: List<String>): String = Gson().toJson(value)

    @TypeConverter
    fun toBaseStats(value: String): BaseStats {
        val objectType: Type = object : TypeToken<BaseStats>() {}.type
        return Gson().fromJson(value, objectType)
    }

    @TypeConverter
    fun fromBaseStats(value: BaseStats): String = Gson().toJson(value)

    @TypeConverter
    fun toListEvolution(value: String): List<Evolution> {
        val objectType: Type = object : TypeToken<List<Evolution>>() {}.type
        return Gson().fromJson(value, objectType)
    }

    @TypeConverter
    fun fromListEvolution(value: List<Evolution>): String = Gson().toJson(value)

//    @TypeConverter
//    fun toListMove(value: String?): List<Move?>? {
//        val objectType: Type = object : TypeToken<List<Move>>() {}.type
//        return Gson().fromJson(value, objectType)
//    }
//
//    @TypeConverter
//    fun fromListMove(value: List<Move?>?): String? = Gson().toJson(value)
//
//    @TypeConverter
//    fun toListLevelUp(value: String): List<LevelUp> {
//        val objectType: Type = object : TypeToken<List<LevelUp>>() {}.type
//        return Gson().fromJson(value, objectType)
//    }
//
//    @TypeConverter
//    fun fromListLevelUp(value: List<LevelUp>): String = Gson().toJson(value)
//
//    @TypeConverter
//    fun toListEggMove(value: String?): List<EggMove>? {
//        val objectType: Type = object : TypeToken<List<EggMove>>() {}.type
//        return Gson().fromJson(value, objectType)
//    }
//
//    @TypeConverter
//    fun fromListEggMove(value: List<EggMove>?): String? = Gson().toJson(value)
//
//    @TypeConverter
//    fun toListTM(value: String?): List<TM>? {
//        val objectType: Type = object : TypeToken<List<TM>>() {}.type
//        return Gson().fromJson(value, objectType)
//    }
//
//    @TypeConverter
//    fun fromListTM(value: List<TM>?): String? = Gson().toJson(value)
//
//    @TypeConverter
//    fun toListTR(value: String?): List<TR>? {
//        val objectType: Type = object : TypeToken<List<TR>>() {}.type
//        return Gson().fromJson(value, objectType)
//    }
//
//    @TypeConverter
//    fun fromListTR(value: List<TR>?): String? = Gson().toJson(value)
//
//    @TypeConverter
//    fun toListTutor(value: String?): List<Tutor>? {
//        val objectType: Type = object : TypeToken<List<Tutor>>() {}.type
//        return Gson().fromJson(value, objectType)
//    }
//
//    @TypeConverter
//    fun fromListTutor(value: List<Tutor>?): String? = Gson().toJson(value)
}