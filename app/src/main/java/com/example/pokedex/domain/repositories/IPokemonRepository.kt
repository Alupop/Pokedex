package com.example.pokedex.domain.repositories

import com.example.pokedex.domain.models.PokemonListModel
import com.example.pokedex.domain.models.PokemonModel


interface IPokemonRepository {
    suspend fun getPokemon(name: String): PokemonModel?

    suspend fun getPokemonList(offset: Int, limit: Int): PokemonListModel

    suspend fun getAllPokemon(): List<String>
}