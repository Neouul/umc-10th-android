package com.neouul.umc10android.week10.data.dto

import com.google.gson.annotations.SerializedName
import com.neouul.umc10android.week10.domain.model.User

data class UserDto(
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("email")
    val email: String? = null,
    @SerializedName("first_name")
    val firstName: String? = null,
    @SerializedName("last_name")
    val lastName: String? = null,
    @SerializedName("avatar")
    val avatarUrl: String? = null,
) {
    fun toDomain(): User {
        return User(
            id = id?.toLong() ?: 0L,
            email = email.orEmpty(),
            firstName = firstName.orEmpty(),
            lastName = lastName.orEmpty(),
            avatarUrl = avatarUrl.orEmpty()
        )
    }
}