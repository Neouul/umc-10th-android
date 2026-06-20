package com.neouul.umc10android.week10.presentation.screen.cart

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.neouul.umc10android.week10.R
import com.neouul.umc10android.week10.presentation.component.CommonButton
import com.neouul.umc10android.week10.ui.AppColors
import com.neouul.umc10android.week10.ui.AppTextStyles

@Composable
fun CartScreen(
    onNavigateToShop: () -> Unit
) {

    // 만약 장바구니가 비어있으면
    EmptyCartScreen(
        onOrderClick = onNavigateToShop
    )
}

@Composable
fun EmptyCartScreen(
    onOrderClick: () -> Unit = {},
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(AppColors.white)
    ) {
        // 1. 중앙 이미지 + 텍스트 영역
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center)
                .padding(bottom = 100.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_bag_circle),
                contentDescription = null,
                modifier = Modifier.size(60.dp)
            )

            Text(
                text = "장바구니가 비어 있습니다.\n제품을 추가하면 여기에 표시됩니다.",
                style = AppTextStyles.mediumTextRegular,
                textAlign = TextAlign.Center,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 40.dp)
                    .padding(top = 27.dp)
            )
        }

        // 2. 하단 버튼 영역
        CommonButton(
            text = "주문하기",
            modifier = Modifier
                .align(Alignment.BottomCenter) // Box의 바닥 중앙에 배치
                .padding(horizontal = 40.dp)
                .padding(bottom = 22.dp),
            onClick = onOrderClick
        )
    }
}


@Preview(showBackground = true)
@Composable
fun CartScreenPreview() {
    CartScreen(onNavigateToShop = {})
}