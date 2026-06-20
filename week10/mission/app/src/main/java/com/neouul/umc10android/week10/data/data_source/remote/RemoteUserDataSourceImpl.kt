package com.neouul.umc10android.week10.data.data_source.remote

import android.util.Log
import com.neouul.umc10android.week10.data.data_source.UserDataSource
import com.neouul.umc10android.week10.data.data_source.remote.api.ReqResService
import com.neouul.umc10android.week10.data.dto.UserData
import com.neouul.umc10android.week10.data.dto.UserDto
import retrofit2.Response
import javax.inject.Inject

class RemoteUserDataSourceImpl @Inject constructor(
    private val service: ReqResService
) : UserDataSource {
    override suspend fun getUsers(page: Int): Response<UserData<List<UserDto>>> {
        Log.d("RemoteUserDataSource", "getUsers request - page: $page")
        val response = service.getUsers(page)
        Log.d("RemoteUserDataSource", "getUsers response - code: ${response.code()}, isSuccessful: ${response.isSuccessful}")
        if (response.isSuccessful) {
            Log.d("RemoteUserDataSource", "getUsers body: ${response.body()}")
        }
        return response
    }

    override suspend fun getUserById(id: Long): Response<UserData<UserDto>> {
        Log.d("RemoteUserDataSource", "getUserById request - id: $id")
        val response = service.getUserById(id)
        Log.d("RemoteUserDataSource", "getUserById response - code: ${response.code()}, isSuccessful: ${response.isSuccessful}")
        if (response.isSuccessful) {
            Log.d("RemoteUserDataSource", "getUserById body: ${response.body()}")
        } else {
            Log.e("RemoteUserDataSource", "getUserById errorBody: ${response.errorBody()?.string()}")
        }
        return response
    }
}
