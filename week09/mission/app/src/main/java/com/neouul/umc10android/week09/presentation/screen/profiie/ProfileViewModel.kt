package com.neouul.umc10android.week09.presentation.screen.profiie

import androidx.lifecycle.viewModelScope
import com.neouul.umc10android.week09.core.base.BaseViewModel
import com.neouul.umc10android.week09.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlinx.coroutines.async

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val userRepository: UserRepository
) : BaseViewModel<ProfileState>(ProfileState()) {

    init {
        loadProfileData()
    }

    private fun loadProfileData() {
        viewModelScope.launch {
            updateState { it.copy(isLoading = true, error = null) }

            val userDeferred = async { userRepository.getUserById(1L) }
            val followingListDeferred = async { userRepository.getUsers(1) }

            val userResult = userDeferred.await()
            val followingListResult = followingListDeferred.await()

            if (userResult.isSuccess && followingListResult.isSuccess) {
                updateState {
                    it.copy(
                        user = userResult.getOrNull(),
                        followingList = followingListResult.getOrDefault(emptyList()),
                        isLoading = false
                    )
                }
            } else {
                val errorMessage = userResult.exceptionOrNull()?.message 
                    ?: followingListResult.exceptionOrNull()?.message 
                    ?: "데이터를 불러오는 중 오류가 발생했습니다."
                
                updateState {
                    it.copy(
                        error = errorMessage,
                        isLoading = false
                    )
                }
            }
        }
    }
}