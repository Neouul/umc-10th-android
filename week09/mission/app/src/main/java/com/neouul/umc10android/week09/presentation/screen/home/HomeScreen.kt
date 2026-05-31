package com.neouul.umc10android.week09.presentation.screen.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.neouul.umc10android.week09.R
import com.neouul.umc10android.week09.domain.model.Product
import com.neouul.umc10android.week09.presentation.component.NewProductItem
import com.neouul.umc10android.week09.ui.AppColors
import com.neouul.umc10android.week09.ui.AppTextStyles
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.lazy.items

@Composable
fun HomeScreen(
    title: String,
    uiState: HomeState = HomeState(),
    onProductClick: (Product) -> Unit = {},
    scrollState: ScrollState = rememberScrollState()
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AppColors.white)
            .verticalScroll(scrollState) // 스크롤 가능하게 설정
    ) {
        // 패딩 설정
        Column(
            modifier = Modifier.padding(horizontal = 17.dp)
        ) {
            // 텍스트 영역 (text_area)
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp)
                    .padding(horizontal = 24.dp, vertical = 50.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = title,
                    style = AppTextStyles.titleTextMedium,
                    modifier = Modifier.fillMaxWidth()
                )
                Text(
                    text = "9월 4일 목요일",
                    color = AppColors.gray1,
                    style = AppTextStyles.largeTextRegular,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            // 이미지 영역
            Image(
                painter = painterResource(id = R.drawable.home_logo),
                contentDescription = "Home Logo",
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.Crop
            )

            // What's new 섹션
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
            ) {
                Text(
                    text = "What’s new",
                    style = AppTextStyles.headerTextMedium,
                    modifier = Modifier.padding(top = 40.dp)
                )
                Text(
                    text = "나이키 최신 상품",
                    style = AppTextStyles.titleTextMedium,
                    color = AppColors.gray1,
                    modifier = Modifier.padding(top = 12.dp, bottom = 22.dp)
                )
            }
        }

        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .height(600.dp),
            horizontalArrangement = Arrangement.spacedBy(6.dp),
        ) {
            item {
                Spacer(Modifier.width(35.dp))
            }

            items(
                items = uiState.products,
                key = { it.id }
            ) { product ->
                NewProductItem(
                    product = product,
                    onItemClick = onProductClick
                )
            }

            item {
                Spacer(Modifier.width(35.dp))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(title = "Discover")
}