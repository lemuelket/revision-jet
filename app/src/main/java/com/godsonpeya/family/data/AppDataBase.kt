package com.godsonpeya.family.data

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.godsonpeya.family.data.dao.MemberDao
import com.godsonpeya.family.data.entity.Member

@Database(entities = [Member::class],version=1, exportSchema = false)
abstract class AppDataBase: RoomDatabase() {
    abstract fun memberDao(): MemberDao
}