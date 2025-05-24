package com.godsonpeya.family.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.godsonpeya.family.data.entity.Member
import com.godsonpeya.family.navigation.AppScreen
import com.godsonpeya.family.ui.viewModel.MemberViewModel
import java.lang.Compiler.enable


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeRegister(navHostController: NavHostController, viewModel:MemberViewModel= hiltViewModel()){

    var nom by remember { mutableStateOf("") }
    var prenom by remember { mutableStateOf("") }
    var sex by remember { mutableStateOf("") }
    var isSaved by remember{mutableStateOf(false)}

    Scaffold(topBar = { TopAppBar(
        title = {},

    ) })
        {innerPadding->

        Column(modifier = Modifier.padding(innerPadding)){

            Text("Register")

            OutlinedTextField(value = nom, onValueChange = {nom=it},
                label = {
                    Text("nom")
                }, singleLine = true
                )

            OutlinedTextField(value = prenom, onValueChange = {prenom=it},
                label = {
                    Text("prenom")
                },singleLine = true
            )

            OutlinedTextField(value = sex, onValueChange = {sex=it},
                label = {
                    Text("sex")
                }, singleLine = true
            )

            Button(onClick = {if(nom.isNotEmpty() && prenom.isNotEmpty() && sex.isNotEmpty()){
                     viewModel.insertMember(member= Member(nom=nom, prenom = prenom, sex=sex))
                     navHostController.navigate(AppScreen.Members.route)
                isSaved
            } }, enabled = (nom.isNotEmpty() && prenom.isNotEmpty() && sex.isNotEmpty())) {
                   Text("Sauvegarder")

            }


        }
    }

}