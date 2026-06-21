package com.neouul.umc10android.week10.data.dto

import com.google.gson.annotations.SerializedName

data class UserData<T>(
    @SerializedName("data")
    val data: T? = null,
    
    // 리스트 조회 시 포함되는 필드들
    @SerializedName("page")
    val page: Int? = null,
    @SerializedName("per_page")
    val perPage: Int? = null,
    @SerializedName("total")
    val total: Int? = null,
    @SerializedName("total_pages")
    val totalPages: Int? = null
)
