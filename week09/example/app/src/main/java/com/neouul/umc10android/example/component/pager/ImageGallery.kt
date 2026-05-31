package com.neouul.umc10android.example.component.pager

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage

@Composable
fun ImageGallery(
    pagerState: PagerState,
    images: List<String>
) {
        HorizontalPager(
        state = pagerState,
        modifier = Modifier.size(512.dp)
    ) { page ->
        AsyncImage(
            model = images[page],
            contentDescription = "이미지 $page",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
    }
}