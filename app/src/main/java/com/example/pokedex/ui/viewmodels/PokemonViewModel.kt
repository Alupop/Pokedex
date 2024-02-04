package com.example.pokedex.ui.viewmodels

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedex.domain.models.PokemonModel
import com.example.pokedex.domain.usecases.PokemonUseCase
import com.example.pokedex.ui.utils.ColorStats
import com.example.pokedex.ui.utils.ColorTypes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class PokemonViewModel @Inject constructor(
    private val pokemonUseCase: PokemonUseCase
) : ViewModel() {

    private var _pokemonModel = MutableLiveData<PokemonModel?>()
    val pokemonModel: MutableLiveData<PokemonModel?> = _pokemonModel

    fun getPokemon(name: String) {
        viewModelScope.launch {
            val loadedPokemon = withContext(Dispatchers.IO) {
                pokemonUseCase.getPokemon(name)
            }
            _pokemonModel.postValue(loadedPokemon)
        }
    }

    private val mapTypesColor = ColorTypes()
    fun getTypeColor(type: String): Color? {
        val typesColor = mapTypesColor[type.lowercase(Locale.ROOT)]
        return typesColor?.color
    }

    private val mapStatsColor = ColorStats()
    fun getStatColors(type: String): Pair<Color, Color>? {
        val statsColor = mapStatsColor[type.lowercase(Locale.ROOT)]
        return statsColor?.let { Pair(it.lightColor, it.darkColor) }
    }
}
