package com.neouul.umc10android.week08.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.neouul.umc10android.week08.R
import com.neouul.umc10android.week08.domain.model.Product
import com.neouul.umc10android.week08.ui.AppColors
import com.neouul.umc10android.week08.ui.AppTextStyles

@Composable
fun ProductItem(
    product: Product,
    modifier: Modifier = Modifier,
    isWishItem: Boolean = false,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
    ) {
        // 상품 이미지
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .background(AppColors.gray2)
        ) {
            AsyncImage(
                model = product.img,
                contentDescription = product.name,
                modifier = Modifier,
                contentScale = ContentScale.Crop
            )

            if (!isWishItem) {
                Box(
                    modifier = Modifier
                        .padding(12.dp)
                        .size(34.dp)
                        .clip(CircleShape)
                        .background(AppColors.white)
                        .align(Alignment.TopEnd)
                ) {
                    Image(
                        painter = if (product.isWished) painterResource(id = R.drawable.ic_heart_full)
                        else painterResource(id = R.drawable.ic_heart_empty),
                        contentDescription = "위시리스트 아이콘",
                        modifier = Modifier
                            .size(20.dp)
                            .align(Alignment.Center),
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
        ) {
            if (product.isBestSeller) {
                Text(
                    text = "BestSeller",
                    style = AppTextStyles.mediumTextMedium.copy(color = AppColors.warning500),
                )
                Spacer(modifier = Modifier.height(4.dp))
            }
            // 상품 이름
            Text(
                text = product.name,
                style = AppTextStyles.mediumTextMedium,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
            )
            Spacer(modifier = Modifier.height(4.dp))

            // 상품 설명
            Text(
                text = product.description,
                style = AppTextStyles.mediumTextRegular.copy(color = AppColors.gray1),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
            )
            Spacer(modifier = Modifier.height(4.dp))

            // 색상 정보
            Text(
                text = "${product.colorNumber} Colours",
                style = AppTextStyles.mediumTextRegular.copy(color = AppColors.gray1),
            )
            Spacer(modifier = Modifier.height(4.dp))

            // 가격
            Text(
                text = product.price,
                style = AppTextStyles.mediumTextMedium,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ProductItemPreview() {
    ProductItem(
        product = Product(
            id = 1,
            name = "Nike Everyday Plus Cushioned",
            description = "Training Ankle Socks (6 Pairs)",
            colorNumber = 5,
            price = "US$10",
            img = "https://static.nike.com/a/images/t_PDP_1728_v1/f_auto,q_auto:eco/76a44558-71e8-466d-837b-94c64379766e/EVERYDAY+PLUS+CUSH+ANKLE+6PR.png",
            isBestSeller = true,
        )
    )
}