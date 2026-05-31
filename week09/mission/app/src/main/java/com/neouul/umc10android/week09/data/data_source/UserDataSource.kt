package com.neouul.umc10android.week09.data.data_source

import com.neouul.umc10android.week09.data.dto.UserData
import com.neouul.umc10android.week09.data.dto.UserDto
import retrofit2.Response

interface UserDataSource {
    suspend fun getUsers(page: Int): Response<UserData<List<UserDto>>>
    suspend fun getUserById(id: Long): Response<UserData<UserDto>>
}