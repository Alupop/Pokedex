package com.example.pokedex.ui.viewmodels

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedex.domain.models.PokemonModel
import com.example.pokedex.domain.usecases.PokemonListUseCase
import com.example.pokedex.domain.usecases.PokemonUseCase
import com.example.pokedex.ui.utils.ColorTypes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    private val pokemonListUseCase: PokemonListUseCase,
    private val pokemonUseCase: PokemonUseCase
) : ViewModel() {

    init {
        viewModelScope.launch {
            getAllPokemon()
            getPokemonList() {}
        }
    }

    private var _pokemonModelListWithInfo = MutableLiveData<List<PokemonModel?>>()
    val pokemonModelListWithInfo: LiveData<List<PokemonModel?>> = _pokemonModelListWithInfo

    private var currentPage = 0
    var isLoading = false
    private val pageSize = 20

    fun getPokemonList(onLoadComplete: () -> Unit) {
        viewModelScope.launch {
            isLoading = true
            val start = currentPage * pageSize
            val loadedPokemonList = withContext(Dispatchers.IO) {
                val pokemonList = pokemonListUseCase.getPokemonList(start, pageSize)
                pokemonList.results.map { pokemon ->
                    pokemonUseCase.getPokemon(pokemon.name)
                }
            }
            _pokemonModelListWithInfo.postValue(_pokemonModelListWithInfo.value.orEmpty() + loadedPokemonList)
            isLoading = false
            currentPage++
            onLoadComplete()
        }
    }

    private var allPokemon: List<String> = emptyList()

    private suspend fun getAllPokemon() {
        allPokemon = withContext(Dispatchers.IO) {
            pokemonListUseCase.getAllPokemon()
        }
    }

    private var _pokemonModelFilteredList = MutableLiveData<List<PokemonModel?>>()
    val pokemonModelFilteredList: LiveData<List<PokemonModel?>> = _pokemonModelFilteredList

    private suspend fun filterPokemonListByName(searchText: String) {
        val filteredList = allPokemon
            .filter { it.startsWith(searchText, ignoreCase = true) }
            .take(10)
            .map { name ->
                pokemonUseCase.getPokemon(name)
            }

        _pokemonModelFilteredList.value = filteredList
    }

    fun performSearch(searchText: String) {
        viewModelScope.launch {
            filterPokemonListByName(searchText)
        }
    }

    private val mapTypesColor = ColorTypes()
    fun getTypeColor(type: String): Color? {
        val typesColor = mapTypesColor[type.lowercase(Locale.ROOT)]
        return typesColor?.color
    }
}