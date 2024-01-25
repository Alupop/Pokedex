package com.example.pokedex.data.repositories

import com.example.pokedex.data.dto.PokemonDTO
import com.example.pokedex.data.dto.PokemonListDTO
import com.example.pokedex.data.sources.remote.api.PokemonApiService
import com.example.pokedex.domain.repositories.IPokemonRepository
import javax.inject.Inject

class ApiRepositoryImpl @Inject constructor(
    private val pokemonApiService: PokemonApiService
): IPokemonRepository {

    override suspend fun getPokemonByName(name: String): PokemonDTO {
        return pokemonApiService.getPokemonByName(name)
    }

    override suspend fun getPokemonList(offset: Int, limit: Int): PokemonListDTO {
        return pokemonApiService.getPokemonList(limit = limit, offset = offset)
    }

    override suspend fun getAllPokemonNames(): List<String> {
        return pokemonApiService.getAllPokemonNames().results.map { it.name }
    }
}