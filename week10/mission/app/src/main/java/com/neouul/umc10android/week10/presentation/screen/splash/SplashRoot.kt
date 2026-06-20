package com.neouul.umc10android.week10.presentation.screen.splash

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import kotlinx.coroutines.delay

@Composable
fun SplashRoot(
    onNavigateToMain: (String) -> Unit
) {
    val title = "UMC week10"

    LaunchedEffect(Unit) {
        delay(2000)
        onNavigateToMain(title)
    }

    SplashScreen(title)
}