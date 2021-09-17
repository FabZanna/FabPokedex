package com.fabulouszanna.fabpokedex.di

import android.content.Context
import androidx.room.Room
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.fabulouszanna.fabpokedex.R
import com.fabulouszanna.fabpokedex.data.AbilityDao
import com.fabulouszanna.fabpokedex.data.PokemonDao
import com.fabulouszanna.fabpokedex.data.PokemonDatabase
import com.fabulouszanna.fabpokedex.other.Constants.POKEMON_DATABASE
import com.fabulouszanna.fabpokedex.repositories.AbilityRepository
import com.fabulouszanna.fabpokedex.repositories.DefaultAbilityRepository
import com.fabulouszanna.fabpokedex.repositories.DefaultPokemonRepository
import com.fabulouszanna.fabpokedex.repositories.PokemonRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideGlideInstance(
        @ApplicationContext context: Context
    ) = Glide.with(context).setDefaultRequestOptions(
        RequestOptions()
            .placeholder(R.drawable.ic_placeholder)
            .error(R.drawable.ic_placeholder)
    )

    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(context, PokemonDatabase::class.java, POKEMON_DATABASE)
        .createFromAsset("database/$POKEMON_DATABASE")
        .fallbackToDestructiveMigration()
        .build()

    @Singleton
    @Provides
    fun providePokemonDao(
        database: PokemonDatabase
    ) = database.pokemonDao()

    @Singleton
    @Provides
    fun providePokemonRepository(dao: PokemonDao) =
        DefaultPokemonRepository(dao) as PokemonRepository


    @Singleton
    @Provides
    fun provideAbilityDao(
        database: PokemonDatabase
    ) = database.abilityDao()

    @Singleton
    @Provides
    fun provideAbilityRepository(dao: AbilityDao) =
        DefaultAbilityRepository(dao) as AbilityRepository
}