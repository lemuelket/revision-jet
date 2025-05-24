package com.godsonpeya.family.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.godsonpeya.family.data.entity.Member
import com.godsonpeya.family.ui.screens.HomeRegister
import com.godsonpeya.family.ui.screens.MemberDetails
import com.godsonpeya.family.ui.screens.MemberList

@Composable
fun AppNavHost(navHostController: NavHostController) {

    NavHost(navHostController,startDestination = AppScreen.Home.route){
        composable(route= AppScreen.Home.route) {
            HomeRegister(navHostController)
        }

        composable(route= AppScreen.Members.route){
            MemberList(navHostController)
        }

        composable(route= AppScreen.Details.route) {navBackStack->
           val memberId= navBackStack.arguments?.getLong("memberId")
            MemberDetails(navHostController, memberId= memberId?:-1L)
        }
    }
}