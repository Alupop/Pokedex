package com.example.pokedex.di

import android.app.Application
import com.example.pokedex.data.repositories.ApiRepositoryImpl
import com.example.pokedex.data.repositories.FallBackRepositoryImpl
import com.example.pokedex.data.repositories.JsonRepositoryImpl
import com.example.pokedex.data.repositories.PokemonApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideRetrofit(): PokemonApiService {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(PokemonApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideApiRepositoryImpl(pokemonApiService: PokemonApiService): ApiRepositoryImpl {
        return ApiRepositoryImpl(pokemonApiService)
    }

    @Provides
    @Singleton
    fun provideJsonRepositoryImpl(application: Application): JsonRepositoryImpl {
        return JsonRepositoryImpl(application)
    }

    @Provides
    @Singleton
    fun provideFallBackRepositoryImpl(
        apiRepositoryImpl: ApiRepositoryImpl,
        jsonRepositoryImpl: JsonRepositoryImpl
    ): FallBackRepositoryImpl {
        return FallBackRepositoryImpl(apiRepositoryImpl, jsonRepositoryImpl)
    }
}