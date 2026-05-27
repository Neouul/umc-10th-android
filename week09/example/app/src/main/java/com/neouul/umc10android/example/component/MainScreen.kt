package com.neouul.umc10android.example.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.neouul.umc10android.example.component.pager.ImageGallery
import com.neouul.umc10android.example.component.pager.PagerDotIndicator

@Composable
fun MainScreen() {
    val images = listOf<String>(
        "https://cdn.pixabay.com/photo/2019/09/16/20/21/cat-cat-4481997_1280.jpg",
        "https://cdn.pixabay.com/photo/2022/03/27/11/23/cat-7094808_1280.jpg",
        "https://cdn.pixabay.com/photo/2023/06/22/15/17/cat-8081701_1280.jpg",
        "https://cdn.pixabay.com/photo/2012/10/12/17/12/cat-61079_1280.jpg",
        "https://cdn.pixabay.com/photo/2018/03/26/02/05/cat-3261420_1280.jpg",
        "https://cdn.pixabay.com/photo/2019/05/08/21/21/cat-4189697_1280.jpg",
    )
    val pagerState = rememberPagerState(pageCount = { images.size })

    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        ImageGallery(
            pagerState = pagerState,
            images = images,
        )

        PagerDotIndicator(
            pagerState = pagerState,
            pageCount = images.size,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
    }
}


@Preview(showBackground = true)
@Composable
private fun MainScreenPreview() {
    MainScreen()
}