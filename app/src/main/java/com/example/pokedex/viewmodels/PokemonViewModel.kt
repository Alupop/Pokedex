package com.example.pokedex.viewmodels

import android.app.Application
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.pokedex.data.repositories.PokemonRepository
import com.example.pokedex.data.models.Pokemon
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Locale

class PokemonViewModel(application: Application): AndroidViewModel(Application()) {

    private var _pokemon = MutableLiveData<Pokemon>()
    val pokemon: LiveData<Pokemon> = _pokemon

    init {
        viewModelScope.launch {
            getPokemon()
        }
    }

    private val pokemonRepository = PokemonRepository(application)

    private fun getPokemon() {
        viewModelScope.launch {
            val loadedPokemon = withContext(Dispatchers.IO) {
                pokemonRepository.getPokemon()
            }
            _pokemon.postValue(loadedPokemon)
        }
    }

    private val mapTypeColor = pokemonRepository.colorTypes()
    fun getTypeColor(type: String): Color? {
        val typeColor = mapTypeColor[type.lowercase(Locale.ROOT)]
        return typeColor?.color
    }

    private val mapStatColor = pokemonRepository.statsTypes()
    fun getStatColor(type: String): Color? {
        val statColor = mapStatColor[type.lowercase(Locale.ROOT)]
        return statColor?.color
    }

}