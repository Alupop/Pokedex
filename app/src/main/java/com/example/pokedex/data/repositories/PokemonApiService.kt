package com.example.pokedex.data.repositories

import com.example.pokedex.data.sources.remote.dto.PokemonDTO
import com.example.pokedex.data.sources.remote.dto.PokemonListDTO
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonApiService {
    @GET("pokemon/{name}/")
    suspend fun getPokemon(@Path("name") name: String): PokemonDTO

    @GET("pokemon")
    suspend fun getPokemonList(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): PokemonListDTO

    @GET("pokemon?limit=1300")
    suspend fun getAllPokemon(): PokemonListDTO
}
