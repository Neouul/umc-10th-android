package com.neouul.umc10android.week08.data.data_source.remote.api

import com.neouul.umc10android.week08.data.dto.UserData
import com.neouul.umc10android.week08.data.dto.UserDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface ReqResService {
    @GET("api/users")
    suspend fun getUsers(
        @Header("x-api-key") token: String,
        @Query("page") page: Int
    ): Response<UserData<List<UserDto>>>

    @GET("api/users/{id}")
    suspend fun getUserById(
        @Header("x-api-key") token: String,
        @Path("id") id: Long
    ): Response<UserData<UserDto>>
}