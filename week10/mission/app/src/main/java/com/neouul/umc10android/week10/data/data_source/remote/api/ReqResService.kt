package com.neouul.umc10android.week10.data.data_source.remote.api

import com.neouul.umc10android.week10.data.dto.UserData
import com.neouul.umc10android.week10.data.dto.UserDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ReqResService {
    @GET("api/users")
    suspend fun getUsers(
        @Query("page") page: Int
    ): Response<UserData<List<UserDto>>>

    @GET("api/users/{id}")
    suspend fun getUserById(
        @Path("id") id: Long
    ): Response<UserData<UserDto>>
}