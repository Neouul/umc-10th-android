package com.neouul.umc10android.week09.domain.repository

import com.neouul.umc10android.week09.domain.model.User

interface UserRepository {
    suspend fun getUsers(page: Int): Result<List<User>>
    suspend fun getUserById(id: Long): Result<User>
}
