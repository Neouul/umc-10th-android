package com.neouul.umc10android.week08.data.repository

import android.util.Log
import com.neouul.umc10android.week08.data.data_source.UserDataSource
import com.neouul.umc10android.week08.domain.model.User
import com.neouul.umc10android.week08.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userDataSource: UserDataSource
) : UserRepository {

    override suspend fun getUsers(page: Int, token: String): Result<List<User>> {
        return try {
            val response = userDataSource.getUsers(page, token)
            val url = response.raw().request.url
            Log.d("UserRepository", "Request URL (getUsers): $url")
            
            if (response.isSuccessful) {
                val userData = response.body()
                val userList = userData?.data?.map { it.toDomain() } ?: emptyList()
                Result.success(userList)
            } else {
                Log.e("UserRepository", "getUsers Error: ${response.code()} ${response.message()}")
                Result.failure(Exception("API Error: ${response.code()} ${response.message()}"))
            }
        } catch (e: Exception) {
            Log.e("UserRepository", "getUsers Exception", e)
            Result.failure(e)
        }
    }

    override suspend fun getUserById(id: Long, token: String): Result<User> {
        return try {
            Log.d("UserRepository", "getUserById calling with token: '$token' (length: ${token.length})")
            val response = userDataSource.getUserById(id, token)
            val url = response.raw().request.url
            Log.d("UserRepository", "Request URL (getUserById): $url")
            Log.d("UserRepository", "Request Headers: ${response.raw().request.headers}")

            if (response.isSuccessful) {
                val userData = response.body()
                val userDto = userData?.data
                if (userDto != null) {
                    Result.success(userDto.toDomain())
                } else {
                    Result.failure(Exception("User not found with id $id"))
                }
            } else {
                Log.e("UserRepository", "getUserById Error: ${response.code()} ${response.message()}")
                Result.failure(Exception("API Error: ${response.code()} ${response.message()}"))
            }
        } catch (e: Exception) {
            Log.e("UserRepository", "getUserById Exception", e)
            Result.failure(e)
        }
    }
}
