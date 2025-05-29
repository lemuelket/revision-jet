package com.godsonpeya.family.ui.screens

import android.app.Dialog
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.godsonpeya.family.R
import com.godsonpeya.family.navigation.AppScreen
import com.godsonpeya.family.ui.viewModel.MemberViewModel
import androidx.compose.ui.window.Dialog
import com.godsonpeya.family.ui.component.MemberAlert

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MemberDetails(navHostController: NavHostController, memberId: String,viewModel: MemberViewModel = hiltViewModel()) {

    var updateAlertDialog by remember { mutableStateOf(false) }
    var deleteAlertDialog by remember { mutableStateOf(false) }

    val member by viewModel.member.collectAsState()

    LaunchedEffect(key1 = memberId) {
        viewModel.getMemberById(memberId)
    }


    Scaffold(topBar = {
        TopAppBar(
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                titleContentColor = MaterialTheme.colorScheme.primary
            ),
            title = {
                Box(modifier = Modifier.fillMaxSize().wrapContentSize(Alignment.Center)) {
                    Text("Member's Details")
                }
            },
            navigationIcon = {
                IconButton(onClick = {
                    navHostController.navigate(AppScreen.Members.route)
                }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = null
                    )
                }
            }, actions = {

                IconButton(onClick = { updateAlertDialog = !updateAlertDialog }) {
                    Icon(imageVector = Icons.Default.Create, contentDescription = "Edit")
                }

                IconButton(onClick = {

                    deleteAlertDialog = !deleteAlertDialog

                }) {
                    Icon(imageVector = Icons.Default.Delete, contentDescription = "delete")
                }
            })
    }) { innerPadding ->
        if (member == null) {
            Text("Aucun membre trouve")
        } else {
            Column(
                modifier = Modifier.fillMaxSize().padding(innerPadding).padding(90.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {

                Image(
                    painter = painterResource(id = R.drawable.member_profile),
                    contentDescription = "",
                    modifier = Modifier.size(150.dp).clip(shape = CircleShape)
                )

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = member!!.nom,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = member!!.prenom,
                    fontStyle = FontStyle.Italic,
                    fontSize = 20.sp
                )

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = member!!.sex,
                    fontStyle = FontStyle.Italic,
                    fontSize = 20.sp
                )
            }
        }

        if (updateAlertDialog) {
                MemberAlert(
                    member,
                    onCancel = { updateAlertDialog = !updateAlertDialog },
                    onConfirm = { nom, prenom, sex ->
                        val updatedMember = member!!.copy(nom = nom, prenom = prenom, sex = sex)
                        viewModel.updateMember(updatedMember)
                        updateAlertDialog=!updateAlertDialog
                    }
                )

            }


        if (deleteAlertDialog) {
            DeleteMemberAlert(onCancel = {deleteAlertDialog=!deleteAlertDialog},
                onConfirm = {
                    viewModel.deleteMember(member!!)
                    deleteAlertDialog=!deleteAlertDialog
                    navHostController.popBackStack()
                })
        }
    }
}

@Composable
private fun DeleteMemberAlert(onCancel:()->Unit,
                              onConfirm:()->Unit){

    Dialog(onDismissRequest = {}) {

        Card(modifier = Modifier.fillMaxWidth()) {

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                Text("Dou you want to delete this Member?")

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(
                        onClick = { onCancel()},
                        colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.error)
                    ) {
                        Text("Cancel")
                    }

                    Spacer(modifier = Modifier.width(15.dp))

                    Button(onClick = { onConfirm() }) {
                        Text("Confirm")
                    }
                }
            }
        }
    }

}

