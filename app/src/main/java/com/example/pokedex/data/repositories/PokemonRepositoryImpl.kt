package com.example.pokedex.data.repositories

import android.app.Application
import com.example.pokedex.data.models.Pokemon
import com.example.pokedex.data.models.StatColor
import com.example.pokedex.data.models.TypeColor
import com.google.gson.Gson
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(private val application: Application): PokemonRepository {
    override fun getPokemon(): Pokemon {
        val gson = Gson()
        val jsonInputStream = application.assets.open("ditto.json")

        return gson.fromJson(jsonInputStream.reader(), Pokemon::class.java)
    }

    override fun colorTypes(): HashMap<String, TypeColor> {
        return hashMapOf(
            "normal" to TypeColor.NORMAL,
            "fire" to TypeColor.FIRE,
            "water" to TypeColor.WATER,
            "grass" to TypeColor.GRASS,
            "electric" to TypeColor.ELECTRIC,
            "ice" to TypeColor.ICE,
            "fighting" to TypeColor.FIGHTING,
            "poison" to TypeColor.POISON,
            "ground" to TypeColor.GROUND,
            "flying" to TypeColor.FLYING,
            "psychic" to TypeColor.PSYCHIC,
            "bug" to TypeColor.BUG,
            "rock" to TypeColor.ROCK,
            "ghost" to TypeColor.GHOST,
            "dragon" to TypeColor.DRAGON,
            "dark" to TypeColor.DARK,
            "steel" to TypeColor.STEEL,
            "fairy" to TypeColor.FAIRY
        )
    }

    override fun statsTypes(): HashMap<String, StatColor> {
        return hashMapOf(
            "hp" to StatColor.HP,
            "attack" to StatColor.ATK,
            "defense" to StatColor.DEF,
            "special-attack" to StatColor.S_ATK,
            "special-defense" to StatColor.S_DEF,
            "speed" to StatColor.SPD
        )
    }

}