package com.neouul.umc10android.week09.presentation.screen.profiie

import androidx.lifecycle.viewModelScope
import com.neouul.umc10android.week09.core.base.BaseViewModel
import com.neouul.umc10android.week09.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val userRepository: UserRepository
) : BaseViewModel<ProfileState>(ProfileState()) {

    init {
        loadUserProfile()
    }

    private fun loadUserProfile() {
        viewModelScope.launch {
            updateState { it.copy(isLoading = true, error = null) }

            userRepository.getUserById(1L).fold(
                onSuccess = { user ->
                    updateState { it.copy(user = user, isLoading = false) }
                },
                onFailure = { exception ->
                    updateState { 
                        it.copy(
                            error = exception.message ?: "알 수 없는 오류가 발생했습니다.", 
                            isLoading = false
                        ) 
                    }
                }
            )
        }
    }
}