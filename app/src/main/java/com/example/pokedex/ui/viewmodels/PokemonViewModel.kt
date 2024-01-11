package com.example.pokedex.ui.viewmodels

import android.app.Application
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.pokedex.data.models.Pokemon
import com.example.pokedex.domain.repositories.IPokemonRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Locale
import javax.inject.Inject

class PokemonViewModel @Inject constructor (application: Application, private val repository: IPokemonRepository) :
    AndroidViewModel(application) {

    private var _pokemon = MutableLiveData<Pokemon>()
    val pokemon: LiveData<Pokemon> = _pokemon

    init {
        viewModelScope.launch {
            getPokemon()
        }
    }

    private fun getPokemon() {
        viewModelScope.launch {

            val loadedPokemon = withContext(Dispatchers.IO) {
                repository.getPokemon()
            }
            _pokemon.postValue(loadedPokemon)

        }
    }

    private val mapTypeColor = repository.colorTypes()
    fun getTypeColor(type: String): Color? {
        val typeColor = mapTypeColor[type.lowercase(Locale.ROOT)]
        return typeColor?.color
    }

    private val mapStatColor = repository.statsTypes()
    fun getStatColor(type: String): Color? {
        val statColor = mapStatColor[type.lowercase(Locale.ROOT)]
        return statColor?.color
    }
}
