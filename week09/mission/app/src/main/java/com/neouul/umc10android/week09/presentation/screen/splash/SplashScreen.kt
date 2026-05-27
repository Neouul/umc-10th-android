package com.neouul.umc10android.week09.presentation.screen.splash

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.neouul.umc10android.week09.ui.AppTextStyles

@Composable
fun SplashScreen(
    title: String = "Nike App",
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = title,
            style = AppTextStyles.titleTextMedium,
        )
    }
}