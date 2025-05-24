package com.godsonpeya.family.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun AppNavHost(navHostController: NavHostController) {

    NavHost(navHostController,startDestination = AppScreen.Home.route){
        composable(route= AppScreen.Home.route) {

        }
    }
}