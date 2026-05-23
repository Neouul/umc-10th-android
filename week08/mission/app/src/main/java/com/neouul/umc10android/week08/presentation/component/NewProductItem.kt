package com.neouul.umc10android.week08.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.neouul.umc10android.week08.domain.model.Product
import com.neouul.umc10android.week08.ui.AppColors
import com.neouul.umc10android.week08.ui.AppTextStyles
import androidx.compose.foundation.clickable

@Composable
fun NewProductItem(
    product: Product,
    modifier: Modifier = Modifier,
    onItemClick: (Product) -> Unit = {},
) {
    Column(
        modifier = modifier
            .width(314.dp)
            .height(718.dp)
            .background(AppColors.gray5)
            .clickable { onItemClick(product) }
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .background(AppColors.gray2),
            model = product.img,
            contentScale = ContentScale.Crop,
            contentDescription = "상품 이미지",
        )

        Text(
            text = product.name,
            style = AppTextStyles.mediumTextMedium,
            modifier = Modifier.padding(top = 20.dp, bottom = 16.dp),
        )

        Text(
            text = product.price,
            style = AppTextStyles.mediumTextMedium,
            color = AppColors.gray1,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun NewProductItemPreview() {
    NewProductItem(
        product = Product(
            id = 1,
            name = "Air Jordan XXXVI",
            price = "US\$185",
            img = "https://static.nike.com/a/images/t_web_pw_592_v2/f_auto/u_126ab356-44d8-4a06-89b4-fcdcc8df0245,c_scale,fl_relative,w_1.0,h_1.0,fl_layer_apply/064afac6-d5c0-4be2-aa48-1ecebae0cf7d/AIR+JORDAN+1+LOW.png",
        )
    )
}