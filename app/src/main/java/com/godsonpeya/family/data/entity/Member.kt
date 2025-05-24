package com.godsonpeya.family.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="members")
class Member (
    @PrimaryKey(autoGenerate = true)
    var id: Long= 0L,
    var nom: String="",
    var prenom: String="",
    var sex : String=""
)
