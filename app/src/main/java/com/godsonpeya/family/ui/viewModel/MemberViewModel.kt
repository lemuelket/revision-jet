package com.godsonpeya.family.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.godsonpeya.family.data.entity.Member
import com.godsonpeya.family.data.repository.MemberRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MemberViewModel @Inject constructor(private val memberRepository: MemberRepository): ViewModel(){

    private var _members= MutableStateFlow<List<Member>>(emptyList())

    val members: StateFlow<List<Member>> = _members

    private var _member= MutableStateFlow<Member?>(null)

    val member: StateFlow<Member?> = _member

    fun getAllMembers(){
        viewModelScope.launch {
           var allMembers= memberRepository.getAllMembers()
            _members.value=allMembers
        }
    }

    fun getMemberById(id: String){
        viewModelScope.launch {
            _member.value=memberRepository.getMemberById(id)
        }
    }

    fun insertMember(member: Member){
        viewModelScope.launch {
            memberRepository.insertMember(member)
        }
    }

    fun updateMember(member: Member){
        viewModelScope.launch {
            memberRepository.updateMember(member)
            getMemberById(member.id)
        }
    }

    fun deleteMember(member: Member){
        viewModelScope.launch {
            memberRepository.deleteMember(member)
            getAllMembers()
        }
    }
}