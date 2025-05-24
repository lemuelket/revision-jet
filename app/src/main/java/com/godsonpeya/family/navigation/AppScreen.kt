package com.godsonpeya.family.navigation


sealed class AppScreen(var route: String){
    data object Home: AppScreen("Register")
    data object Members : AppScreen("members")

    data object Details : AppScreen("Details/{memberId}") {
        fun createRoute(memberId: Long) = "Details/$memberId"
    }
}