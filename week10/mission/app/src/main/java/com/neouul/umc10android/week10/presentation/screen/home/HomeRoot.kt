package com.neouul.umc10android.week10.presentation.screen.home

import android.app.Activity
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.neouul.umc10android.week10.domain.model.Product

@Composable
fun HomeRoot(
    title: String,
    onNavigateToDetail: (Product) -> Unit = {},
    viewModel: HomeViewModel = hiltViewModel()
) {
    val scrollState = rememberScrollState()
    val context = LocalContext.current
    var backPressedTime by remember { mutableLongStateOf(0L) }
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    BackHandler {
        if (System.currentTimeMillis() - backPressedTime < 2000) {
            (context as? Activity)?.finish()
        } else {
            backPressedTime = System.currentTimeMillis()
            Toast.makeText(context, "'뒤로' 버튼을 한 번 더 누르시면 종료됩니다.", Toast.LENGTH_SHORT).show()
        }
    }

    HomeScreen(
        title = title,
        uiState = uiState,
        onProductClick = onNavigateToDetail,
        scrollState = scrollState
    )
}
