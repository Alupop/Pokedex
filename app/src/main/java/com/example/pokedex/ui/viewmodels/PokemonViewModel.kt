package com.example.pokedex.ui.viewmodels

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedex.data.dto.PokemonDTO
import com.example.pokedex.data.repositories.ApiRepositoryImpl
import com.example.pokedex.data.repositories.JsonRepositoryImpl
import com.example.pokedex.ui.utils.ColorStats
import com.example.pokedex.ui.utils.ColorTypes
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Locale
import javax.inject.Inject

class PokemonViewModel @Inject constructor (
    private val apiRepositoryImpl: ApiRepositoryImpl,
    private val jsonRepositoryImpl: JsonRepositoryImpl) : ViewModel() {

    private var _pokemon = MutableLiveData<PokemonDTO>()
    val pokemon: LiveData<PokemonDTO> = _pokemon

    fun getPokemon(name: String) {
        viewModelScope.launch {
            try {
                val loadedPokemon = withContext(Dispatchers.IO) {
                    apiRepositoryImpl.getPokemonByName(name)
                }
                _pokemon.postValue(loadedPokemon)

            } catch (e: Exception) {
                val loadedPokemon = withContext(Dispatchers.IO) {
                    jsonRepositoryImpl.getPokemonByName(name)
                }
                _pokemon.postValue(loadedPokemon)
            }
        }
    }

    private val mapTypeColor = ColorTypes()
    fun getTypeColor(type: String): Color? {
        val typeColor = mapTypeColor[type.lowercase(Locale.ROOT)]
        return typeColor?.color
    }

    private val mapStatColor = ColorStats()
    fun getStatColor(type: String): Color? {
        val statColor = mapStatColor[type.lowercase(Locale.ROOT)]
        return statColor?.color
    }
}
