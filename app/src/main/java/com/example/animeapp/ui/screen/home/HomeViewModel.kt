package com.example.animeapp.ui.screen.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.animeapp.data.AnimeRepository
import com.example.animeapp.model.Anime
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class HomeViewModel(private val repository: AnimeRepository): ViewModel() {
    private val _groupedAnimes = MutableStateFlow(
        repository.getAnime()
            .sortedBy { it.name }
            .groupBy { it.name[0] }
    )
    val groupedAnimes: StateFlow<Map<Char, List<Anime>>> get() = _groupedAnimes

    private val _query = mutableStateOf("")
    val query: State<String> get() = _query


    fun search(newQuery: String) {
        _query.value = newQuery
        _groupedAnimes.value = repository.searchHeroes(_query.value)
            .sortedBy { it.name }
            .groupBy { it.name[0] }
    }
}

class ViewModelFactory(private val repository: AnimeRepository): ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}