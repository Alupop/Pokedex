package com.example.pokedex.domain.repositories

import com.example.pokedex.data.dto.PokemonDTO
import com.example.pokedex.data.dto.PokemonListDTO


interface IPokemonRepository {
    suspend fun getPokemonByName(name: String): PokemonDTO

    suspend fun getPokemonList(offset: Int, limit: Int): PokemonListDTO

    suspend fun getAllPokemonNames(): List<String>
}