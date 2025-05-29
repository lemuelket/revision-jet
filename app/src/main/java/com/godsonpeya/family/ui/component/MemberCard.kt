package com.godsonpeya.family.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.godsonpeya.family.R
import com.godsonpeya.family.data.entity.Member

@Composable
fun MemberCard(member: Member,onClick:()->Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
            .clickable { onClick() },
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            painter = painterResource(id = R.drawable.member_profile),
            contentDescription = "member",
            contentScale = ContentScale.Crop,
            modifier = Modifier.size(100.dp).clip(shape = CircleShape)
        )

        Spacer(modifier = Modifier.width(15.dp))

        Column() {
            Text(member.nom)

            Spacer(modifier = Modifier.height(10.dp))

            Text(member.prenom)

            Spacer(modifier = Modifier.height(10.dp))

            Text(member.sex)
        }
    }
}