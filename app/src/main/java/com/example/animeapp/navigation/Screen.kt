package com.example.animeapp.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Profile : Screen("profile")
    object DetailAnime : Screen("home/{detailId}") {
        fun createRoute(detailId: String) = "home/$detailId"
    }
}
