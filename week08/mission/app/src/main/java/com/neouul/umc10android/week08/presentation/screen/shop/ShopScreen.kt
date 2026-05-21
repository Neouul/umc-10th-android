package com.neouul.umc10android.week08.presentation.screen.shop

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults.SecondaryIndicator
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.neouul.umc10android.week08.data.data_source.local.DummyDataSource
import com.neouul.umc10android.week08.presentation.component.ProductItem
import com.neouul.umc10android.week08.ui.AppColors
import com.neouul.umc10android.week08.ui.AppTextStyles

@Composable
fun ShopScreen(

) {
    var selectedTabIndex by remember { mutableIntStateOf(0) }
    val tabs = listOf("전체", "Tops & T-Shirts", "sale")

    // 가상의 카테고리 할당 데이터
    val products = remember {
        DummyDataSource.dummyWishProducts.mapIndexed { index, product ->
            when (index) {
                1, 4 -> product.copy(category = "sale", isBestSeller = index == 1)
                0, 2, 3, 5 -> product.copy(category = "Tops", isBestSeller = index == 0)
                else -> product
            }
        }
    }

    val filteredProducts = when (selectedTabIndex) {
        1 -> products.filter { it.category == "Tops" }
        2 -> products.filter { it.category == "sale" }
        else -> products
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 15.dp)
            .background(AppColors.white)
    ) {
        Spacer(modifier = Modifier.height(15.dp))

        ScrollableTabRow(
            selectedTabIndex = selectedTabIndex,
            modifier = Modifier
                .padding(start = 9.dp),
            containerColor = Color.Transparent,
            contentColor = AppColors.black,
            edgePadding = 0.dp,
            indicator = { tabPositions ->
                if (selectedTabIndex < tabPositions.size) {
                    SecondaryIndicator(
                        modifier = Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex]),
                        height = 2.dp,
                        color = AppColors.black
                    )
                }
            },
            divider = {}, // 구분선 제거
        ) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    selected = selectedTabIndex == index,
                    onClick = { selectedTabIndex = index },
                    modifier = Modifier.height(56.dp),
                ) {
                    Text(
                        text = title,
                        style = AppTextStyles.headerTextRegular,
                        modifier = Modifier.padding(horizontal = 24.dp),
                        color = if (selectedTabIndex == index) AppColors.black else AppColors.gray1
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(horizontal = 18.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            items(filteredProducts) { product ->
                ProductItem(
                    product = product,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ShopScreenPreview() {
    ShopScreen()
}