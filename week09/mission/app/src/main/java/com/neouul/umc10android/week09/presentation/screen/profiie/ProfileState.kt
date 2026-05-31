package com.neouul.umc10android.week09.presentation.screen.profiie

import com.neouul.umc10android.week09.core.base.UiState
import com.neouul.umc10android.week09.domain.model.User

data class ProfileState(
    val user: User? = null,
    val isLoading: Boolean = false,
    val error: String? = null
) : UiState