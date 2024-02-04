package com.example.pokedex.data.repositories

import com.example.pokedex.domain.models.PokemonListModel
import com.example.pokedex.domain.models.PokemonModel
import com.example.pokedex.domain.repositories.IPokemonRepository
import com.example.pokedex.mappers.dtotomodel.mapPokemonDTOToModel
import com.example.pokedex.mappers.dtotomodel.mapPokemonListDTOToModel
import javax.inject.Inject

class ApiRepositoryImpl @Inject constructor(
    private val pokemonApiService: PokemonApiService
) : IPokemonRepository {

    override suspend fun getPokemon(name: String): PokemonModel {
        val pokemonDTO = pokemonApiService.getPokemon(name)
        return mapPokemonDTOToModel(pokemonDTO)
    }

    override suspend fun getPokemonList(offset: Int, limit: Int): PokemonListModel {
        val pokemonListDTO = pokemonApiService.getPokemonList(limit = limit, offset = offset)
        return mapPokemonListDTOToModel(pokemonListDTO)
    }

    override suspend fun getAllPokemon(): List<String> {
        return pokemonApiService.getAllPokemon().results.map { it.name }
    }
}