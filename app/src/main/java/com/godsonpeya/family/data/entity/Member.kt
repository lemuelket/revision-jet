package com.godsonpeya.family.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID


@Entity(tableName="members")
data class Member (
    @PrimaryKey
    var id: String= UUID.randomUUID().toString(),
    var nom: String="",
    var prenom: String="",
    var sex : String=""
)
