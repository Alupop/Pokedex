package com.example.pokedex.di

import android.app.Application
import com.example.pokedex.domain.repositories.IPokemonRepository
import com.example.pokedex.domain.repositories.PokemonRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped

@Module
@InstallIn(ActivityComponent::class)
object DaggerModule {
    @Provides
    @ActivityScoped
    fun providePokemonRepository(): IPokemonRepository {
        return PokemonRepository(Application())
    }
}