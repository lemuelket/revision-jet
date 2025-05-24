package com.godsonpeya.family.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.godsonpeya.family.ui.viewModel.MemberViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MemberList(navHostController: NavHostController, viewModel: MemberViewModel= hiltViewModel()) {

    val members by viewModel.members.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getAllMembers()
    }

    Scaffold(topBar = {
        TopAppBar(title = {Text("")})
    }){ innerPadding->
        LazyColumn (modifier = Modifier.padding(innerPadding)){
           items(members){

            }
        }
    }
}