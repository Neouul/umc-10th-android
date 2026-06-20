package com.neouul.umc10android.week10.presentation.screen.profiie

import com.neouul.umc10android.week10.core.base.UiState
import com.neouul.umc10android.week10.domain.model.User

data class ProfileState(
    val user: User? = null,
    val followingList: List<User> = emptyList(),
    val selectedUser: User? = null,
    val isLoading: Boolean = false,
    val error: String? = null
) : UiState