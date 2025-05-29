package com.godsonpeya.family.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.godsonpeya.family.data.entity.Member

@Dao
interface MemberDao {

    @Query("SELECT * FROM members ORDER BY id DESC")
    suspend fun getMembers(): List<Member>

    @Query("SELECT* FROM members where id=:id")
    suspend fun getMemberById(id: String) : Member

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMember(vararg member: Member)

    @Update
    suspend fun updateMember(member: Member)

    @Delete
    suspend fun deleteMember(member: Member)
}