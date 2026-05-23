package com.neouul.umc10android.week08.domain.model

data class User(
    val id: Long,
    val email: String,
    val firstName: String,
    val lastName: String,
    val avatarUrl: String
) {
    val nickName: String
        get() = "$firstName $lastName"
}