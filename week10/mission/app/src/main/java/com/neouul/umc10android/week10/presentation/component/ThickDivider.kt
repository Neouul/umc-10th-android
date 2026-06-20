package com.neouul.umc10android.week10.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.neouul.umc10android.week10.ui.AppColors

@Composable
fun ThickDivider() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height((7.55).dp)
            .background(AppColors.gray5)
    )
}