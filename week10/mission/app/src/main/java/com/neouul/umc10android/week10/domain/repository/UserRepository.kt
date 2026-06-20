package com.neouul.umc10android.week10.domain.repository

import com.neouul.umc10android.week10.domain.model.User

interface UserRepository {
    suspend fun getUsers(page: Int): Result<List<User>>
    suspend fun getUserById(id: Long): Result<User>
}
