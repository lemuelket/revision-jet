package com.godsonpeya.family.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.godsonpeya.family.data.entity.Member

@Composable
fun MemberAlert(member: Member?=null,
                onCancel:()->Unit,
                onConfirm:(nom:String, prenom:String, sex:String)->Unit
){

    var nom by remember { mutableStateOf(member?.nom?:"") }
    var prenom by remember { mutableStateOf(member?.prenom?:"") }
    var sex by remember { mutableStateOf(member?.sex?:"") }
    Dialog(onDismissRequest = {}) {

        Card(modifier = Modifier.fillMaxWidth()) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text("Register", textAlign = TextAlign.Center)

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

                Spacer(modifier = Modifier.height(10.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {

                    Button(
                        onClick = { onCancel() },
                        colors = ButtonDefaults.buttonColors(contentColor = MaterialTheme.colorScheme.error)
                    ) {
                        Text("Cancel")
                    }

                    Spacer(modifier = Modifier.width(10.dp))

                    Button(
                        onClick = {
                            onConfirm(nom,prenom,sex)
                        },
                        modifier = Modifier.size(90.dp, 40.dp)
                    ) {
                        Text("Add")
                    }
                }
            }
        }
    }

}

