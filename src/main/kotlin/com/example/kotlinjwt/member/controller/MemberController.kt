package com.example.kotlinjwt.member.controller

import com.example.kotlinjwt.common.authority.TokenInfo
import com.example.kotlinjwt.common.dto.BaseResponse
import com.example.kotlinjwt.common.dto.CustomUser
import com.example.kotlinjwt.member.dto.LoginDto
import com.example.kotlinjwt.member.dto.MemberDtoRequest
import com.example.kotlinjwt.member.dto.MemberDtoResponse
import com.example.kotlinjwt.member.service.MemberService
import jakarta.validation.Valid
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/api/member")
@RestController
class MemberController(
    private val memberService: MemberService
) {

    /**
     * 회원가입
     */
    @PostMapping("/signup")
    fun signYp(@RequestBody @Valid memberDtoRequest: MemberDtoRequest): BaseResponse<Unit> =
        BaseResponse(message = memberService.signUp(memberDtoRequest))

    /**
     * 로그인
     */
    @PostMapping("/login")
    fun login(@RequestBody @Valid loginDto: LoginDto): BaseResponse<TokenInfo> =
        BaseResponse(data = memberService.login(loginDto))

    /**
     * 내 정보 보기
     */
    @GetMapping("/info")
    fun searchMyInfo(): BaseResponse<MemberDtoResponse> {
        val userId = (SecurityContextHolder.getContext().authentication.principal as CustomUser).userId
        return BaseResponse(data = memberService.searchMyInfo(userId))
    }

    /**
     * 내 정보 수정
     */
    @PutMapping("/info")
    fun saveMyInfo(@RequestBody @Valid memberDtoRequest: MemberDtoRequest): BaseResponse<Unit> {
        val userId = (SecurityContextHolder.getContext().authentication.principal as CustomUser).userId
        memberDtoRequest.id = userId
        val resultMsg = memberService.saveMyInfo(memberDtoRequest)
        return BaseResponse(message = resultMsg)
    }

}