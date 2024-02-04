package com.example.pokedex.data.repositories

import com.example.pokedex.domain.models.PokemonListModel
import com.example.pokedex.domain.models.PokemonModel
import com.example.pokedex.domain.repositories.IPokemonRepository
import javax.inject.Inject

class FallBackRepositoryImpl @Inject constructor(
    private val apiRepositoryImpl: ApiRepositoryImpl,
    private val jsonRepositoryImpl: JsonRepositoryImpl
) : IPokemonRepository {
    override suspend fun getPokemon(name: String): PokemonModel? {
        return try {
            apiRepositoryImpl.getPokemon(name)
        } catch (e: Exception) {
            return jsonRepositoryImpl.getPokemon(name)
        }
    }

    override suspend fun getPokemonList(offset: Int, limit: Int): PokemonListModel {
        return try {
            apiRepositoryImpl.getPokemonList(offset, limit)
        } catch (e: Exception) {
            jsonRepositoryImpl.getPokemonList(offset, limit)
        }
    }

    override suspend fun getAllPokemon(): List<String> {
        return try {
            apiRepositoryImpl.getAllPokemon()
        } catch (e: Exception) {
            jsonRepositoryImpl.getAllPokemon()
        }
    }

}