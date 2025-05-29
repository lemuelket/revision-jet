package com.godsonpeya.family.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.godsonpeya.family.ui.screens.HomeRegister
import com.godsonpeya.family.ui.screens.MemberDetails
import com.godsonpeya.family.ui.screens.MemberList

@RequiresApi(Build.VERSION_CODES.Q)
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
           val memberId= navBackStack.arguments?.getString("memberId")
            MemberDetails(navHostController, memberId= memberId!!)
        }
    }
}