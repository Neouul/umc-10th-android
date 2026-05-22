package com.neouul.umc10android.week08.data.data_source.remote

import com.neouul.umc10android.week08.data.data_source.UserDataSource
import com.neouul.umc10android.week08.data.data_source.remote.api.ReqResService
import com.neouul.umc10android.week08.data.dto.UserData
import com.neouul.umc10android.week08.data.dto.UserDto
import retrofit2.Response
import javax.inject.Inject

class RemoteUserDataSourceImpl @Inject constructor(
    private val service: ReqResService
) : UserDataSource {
    override suspend fun getUsers(page: Int, token: String): Response<UserData<List<UserDto>>> {
        return service.getUsers(token, page)
    }

    override suspend fun getUserById(id: Long, token: String): Response<UserData<UserDto>> {
        return service.getUserById(token, id)
    }
}
