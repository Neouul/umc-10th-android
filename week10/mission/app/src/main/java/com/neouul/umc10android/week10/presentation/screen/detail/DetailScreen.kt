package com.neouul.umc10android.week10.presentation.screen.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.neouul.umc10android.week10.R
import com.neouul.umc10android.week10.domain.model.Product
import com.neouul.umc10android.week10.presentation.component.CommonButton
import com.neouul.umc10android.week10.presentation.component.DetailTopBar
import com.neouul.umc10android.week10.ui.AppColors
import com.neouul.umc10android.week10.ui.AppTextStyles

@Composable
fun DetailScreen(
    uiState: DetailState,
    onBackClick: () -> Unit,
    onWishClick: () -> Unit
) {
    Scaffold(
        topBar = {
            DetailTopBar(
                title = uiState.product?.name ?: "",
                onBackClick = onBackClick
            )
        },
        containerColor = AppColors.white
    ) { innerPadding ->
        if (uiState.isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = AppColors.black)
            }
        } else if (uiState.product != null) {
            // 상세 페이지 화면
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 18.dp, vertical = 20.dp)
            ) {
                val product = uiState.product

                // 이미지 영역
                AsyncImage(
                    model = product.img.ifEmpty { null },
                    contentDescription = product.name,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(366.dp),
                    contentScale = ContentScale.Crop,
                    placeholder = painterResource(id = R.drawable.img_detail),
                    error = painterResource(id = R.drawable.img_detail)
                )

                // 텍스트 영역
                Column(
                    modifier = Modifier
                        .padding(horizontal = 20.dp, vertical = 35.dp)
                ) {
                    Text(
                        text = product.category,
                        style = AppTextStyles.headerTextRegular,
                    )

                    Text(
                        text = product.name,
                        style = AppTextStyles.titleTextMedium,
                        modifier = Modifier.padding(top = 6.dp),
                    )

                    Text(
                        text = product.price,
                        style = AppTextStyles.headerTextRegular.copy(fontWeight = FontWeight.Medium),
                        modifier = Modifier.padding(top = 16.dp),
                    )

                    Text(
                        text = product.detailDescription.ifEmpty { product.description },
                        style = AppTextStyles.largeTextRegular,
                        modifier = Modifier.padding(vertical = 30.dp),
                    )

                    Text(
                        text = "View Product Details",
                        style = AppTextStyles.largeTextRegular,
                        color = AppColors.gray1,
                    )
                }

                // 버튼 영역
                CommonButton(
                    text = "사이즈 선택",
                    icon = R.drawable.ic_down_arrow,
                    isOutlined = true,
                    modifier = Modifier.padding(horizontal = 24.dp),
                )

                Spacer(modifier = Modifier.height(20.dp))

                CommonButton(
                    text = "장바구니에 추가",
                    modifier = Modifier.padding(horizontal = 24.dp)
                )

                Spacer(modifier = Modifier.height(20.dp))

                CommonButton(
                    text = "위시리스트",
                    icon = R.drawable.ic_heart_straight,
                    isOutlined = true,
                    modifier = Modifier.padding(horizontal = 24.dp),
                    onClick = onWishClick
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DetailScreenPreview() {
    DetailScreen(
        uiState = DetailState(
            product = Product(
                id = 1,
                name = "Nike Everyday Plus Cushioned",
                category = "Training Crew Socks",
                price = "US\$10",
                description = "The Nike Everyday Plus Cushioned Socks bring comfort to your workout...",
                isWished = false
            )
        ),
        onBackClick = {},
        onWishClick = {}
    )
}