package com.example.kotlinjwt.member.repository

import com.example.kotlinjwt.member.entity.Member
import com.example.kotlinjwt.member.entity.MemberRole
import org.springframework.data.jpa.repository.JpaRepository

interface MemberRepository: JpaRepository<Member, Long> {
    fun findByLoginId(loginId: String): Member?
}

interface MemberRoleRepository: JpaRepository<MemberRole, Long>