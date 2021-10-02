package com.fabulouszanna.fabpokedex.di

import android.content.Context
import androidx.room.Room
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.fabulouszanna.fabpokedex.R
import com.fabulouszanna.fabpokedex.features.pokemon.data.db.PokemonDatabase
import com.fabulouszanna.fabpokedex.features.pokemon.data.repository.PokemonRepositoryImpl
import com.fabulouszanna.fabpokedex.features.pokemon.domain.repository.PokemonRepository
import com.fabulouszanna.fabpokedex.features.pokemon.domain.use_cases.GetPokemonListUseCase
import com.fabulouszanna.fabpokedex.features.pokemon.domain.use_cases.PokemonUseCases
import com.fabulouszanna.fabpokedex.features.pokemon.domain.repository.AbilityRepository
import com.fabulouszanna.fabpokedex.features.pokemon.data.repository.AbilityRepositoryImpl
import com.fabulouszanna.fabpokedex.features.pokemon.domain.use_cases.GetPokemonUseCase
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
    ) = Room.databaseBuilder(context, PokemonDatabase::class.java, PokemonDatabase.DATABASE_NAME)
        .createFromAsset("database/${PokemonDatabase.DATABASE_NAME}")
        .fallbackToDestructiveMigration()
        .build()

    @Singleton
    @Provides
    fun providePokemonRepository(db: PokemonDatabase): PokemonRepository =
        PokemonRepositoryImpl(db.pokemonDao)


    @Singleton
    @Provides
    fun provideAbilityRepository(db: PokemonDatabase): AbilityRepository =
        AbilityRepositoryImpl(db.abilityDao)

    @Singleton
    @Provides
    fun providePokemonUseCases(repository: PokemonRepository): PokemonUseCases {
        return PokemonUseCases(
            getPokemonListUseCase = GetPokemonListUseCase(repository),
            getPokemonUseCase = GetPokemonUseCase(repository)
        )
    }
}