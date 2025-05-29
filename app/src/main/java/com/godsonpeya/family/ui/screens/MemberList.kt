package com.godsonpeya.family.ui.screens


import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.godsonpeya.family.data.entity.Member
import com.godsonpeya.family.navigation.AppScreen
import com.godsonpeya.family.ui.component.MemberAlert
import com.godsonpeya.family.ui.component.MemberCard
import com.godsonpeya.family.ui.viewModel.MemberViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MemberList(navHostController: NavHostController, viewModel: MemberViewModel= hiltViewModel()) {

    val members by viewModel.members.collectAsState()
    var createAlertDialog by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        viewModel.getAllMembers()
    }

    Scaffold(topBar = {
        TopAppBar(title = {Text("Member's List")},
            colors = TopAppBarDefaults.topAppBarColors(MaterialTheme.colorScheme.secondary),
           navigationIcon = {
               IconButton(onClick = {navHostController.navigate(AppScreen.Home.route)}) {
                   Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "add")
               }
           },
            actions = {
                IconButton(onClick = {createAlertDialog=!createAlertDialog}) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = "add")
                }
            })
    } ){ innerPadding->

        LazyColumn (modifier = Modifier.padding(innerPadding)){
           items(members){member->
             MemberCard(member,
                 onClick = {navHostController.navigate(AppScreen.Details.createRoute(member.id))}
             )
               HorizontalDivider()
            }
        }

        if(createAlertDialog){
            MemberAlert(
                onCancel = { createAlertDialog = !createAlertDialog },
                onConfirm = { nom, prenom, sex ->
                    viewModel.insertMember(Member(nom = nom, prenom = prenom, sex = sex))
                    viewModel.getAllMembers()

                    createAlertDialog= !createAlertDialog
                },

            )
        }
    }
}

