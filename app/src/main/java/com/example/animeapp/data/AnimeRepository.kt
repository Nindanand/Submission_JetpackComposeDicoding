package com.example.animeapp.data

import com.example.animeapp.model.Anime
import com.example.animeapp.model.AnimeData

class AnimeRepository {
    fun getAnime(): List<Anime> {
        return AnimeData.animes
    }

    fun searchHeroes(query: String): List<Anime> {
        return AnimeData.animes.filter {
            it.name.contains(query, ignoreCase = true)
        }
    }
}