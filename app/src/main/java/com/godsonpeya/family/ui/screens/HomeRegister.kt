package com.godsonpeya.family.ui.screens


import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.godsonpeya.family.data.entity.Member
import com.godsonpeya.family.navigation.AppScreen
import com.godsonpeya.family.ui.viewModel.MemberViewModel


@RequiresApi(Build.VERSION_CODES.Q)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeRegister(navHostController: NavHostController, viewModel:MemberViewModel= hiltViewModel()){

    var nom by remember { mutableStateOf("") }
    var prenom by remember { mutableStateOf("") }
    var sex by remember { mutableStateOf("") }
    var isSaved by remember{mutableStateOf(false)}

    Scaffold(topBar = { TopAppBar(
        title = {},
        navigationIcon = {
            IconButton(onClick ={navHostController.navigate(AppScreen.Members.route)} ) {
                Icon(imageVector = Icons.AutoMirrored.Default.ArrowForward, contentDescription = "list")
            }
        }

    ) }){innerPadding->

        Column(modifier = Modifier.padding(innerPadding).fillMaxSize().background(color = MaterialTheme.colorScheme.onPrimary ),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally){

            Text("Register",
                modifier = Modifier.padding(15.dp),
                fontSize = 30.sp,
                )

            OutlinedTextField(
                value = nom, onValueChange = { nom = it },
                label = {
                    Text("nom")
                }, singleLine = true
            )

            OutlinedTextField(
                value = prenom, onValueChange = { prenom = it },
                label = {
                    Text("prenom")
                }, singleLine = true
            )

            OutlinedTextField(
                value = sex, onValueChange = { sex = it },
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

