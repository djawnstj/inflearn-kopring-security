package com.example.kotlinjwt.common.authority

data class TokenInfo(
    val grantType: String,
    val accessToken: String,
)