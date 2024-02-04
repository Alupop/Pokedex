package com.example.pokedex.domain.usecases

import com.example.pokedex.domain.models.PokemonListModel
import com.example.pokedex.domain.repositories.IPokemonRepository
import javax.inject.Inject

class PokemonListUseCase @Inject constructor(
    private val repository: IPokemonRepository
) {
    suspend fun getPokemonList(offset: Int, limit: Int): PokemonListModel {
        return repository.getPokemonList(offset, limit)
    }

    suspend fun getAllPokemon(): List<String> {
        return repository.getAllPokemon()
    }
}