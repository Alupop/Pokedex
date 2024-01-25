package com.example.pokedex.data.repositories

import android.app.Application
import com.example.pokedex.data.dto.PokemonDTO
import com.example.pokedex.data.dto.PokemonListDTO
import com.example.pokedex.domain.repositories.IPokemonRepository
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.BufferedReader
import javax.inject.Inject

class JsonRepositoryImpl @Inject constructor(
    private val application: Application): IPokemonRepository {

        private val gson = Gson()

    override suspend fun getPokemonByName(name: String): PokemonDTO {
        val jsonInputStream = application.assets.open("$name.json")
        return gson.fromJson(jsonInputStream.reader(), PokemonDTO::class.java)
    }

    override suspend fun getPokemonList(offset: Int, limit: Int): PokemonListDTO {
        val jsonInputStream = application.assets.open("pokemon_list.json")
        val reader = BufferedReader(jsonInputStream.reader())

        reader.use { reader ->
            val jsonString = StringBuilder()
            var line: String?
            while (reader.readLine().also { line = it } != null) {
                jsonString.append(line)
            }

            val type = object : TypeToken<PokemonListDTO>() {}.type
            val pokemonList = gson.fromJson<PokemonListDTO>(jsonString.toString(), type)

            val startIndex = offset.coerceAtLeast(0)
            val endIndex = (offset + limit).coerceAtMost(pokemonList.results.size)
            val trimmedResults = pokemonList.results.subList(startIndex, endIndex)

            return PokemonListDTO(trimmedResults)
        }
    }


    override suspend fun getAllPokemonNames(): List<String> {
        val jsonInputStream = application.assets.open("pokemon_list.json")
        val reader = BufferedReader(jsonInputStream.reader())
        val type = object : TypeToken<PokemonListDTO>() {}.type
        val pokemonList = gson.fromJson<PokemonListDTO>(reader, type)
        return pokemonList.results.map { it.name }
    }
}