package com.godsonpeya.family.data.repository

import com.godsonpeya.family.data.dao.MemberDao
import com.godsonpeya.family.data.entity.Member
import jakarta.inject.Inject

class MemberRepository @Inject constructor(private val memberDao: MemberDao) {

    suspend fun getAllMembers(): List<Member> = memberDao.getMembers()

    suspend fun getMemberById(id: String): Member= memberDao.getMemberById(id)

    suspend fun insertMember(member: Member)= memberDao.insertMember(member)

    suspend fun updateMember(member: Member)= memberDao.updateMember(member)

    suspend fun deleteMember(member: Member)= memberDao.deleteMember(member)
}