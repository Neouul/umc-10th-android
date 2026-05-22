package com.neouul.umc10android.week08.data.dto

import com.google.gson.annotations.SerializedName

data class UserData<T>(
    @SerializedName("data")
    val data: T? = null,
    @SerializedName("page")
    val page: Int? = null,
    @SerializedName("total_pages")
    val totalPages: Int? = null,
)
