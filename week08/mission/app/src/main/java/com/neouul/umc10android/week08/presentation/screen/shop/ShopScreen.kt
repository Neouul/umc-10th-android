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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.neouul.umc10android.week08.domain.model.Product
import com.neouul.umc10android.week08.presentation.component.ProductItem
import com.neouul.umc10android.week08.ui.AppColors
import com.neouul.umc10android.week08.ui.AppTextStyles

@Composable
fun ShopScreen(
    uiState: ShopState,
    onTabSelected: (Int) -> Unit,
    onWishClick: (Product) -> Unit,
    onProductClick: (Product) -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 15.dp)
            .background(AppColors.white)
    ) {
        Spacer(modifier = Modifier.height(15.dp))

        ScrollableTabRow(
            selectedTabIndex = uiState.selectedTabIndex,
            modifier = Modifier
                .padding(start = 9.dp),
            containerColor = Color.Transparent,
            contentColor = AppColors.black,
            edgePadding = 0.dp,
            indicator = { tabPositions ->
                if (uiState.selectedTabIndex < tabPositions.size) {
                    SecondaryIndicator(
                        modifier = Modifier.tabIndicatorOffset(tabPositions[uiState.selectedTabIndex]),
                        height = 2.dp,
                        color = AppColors.black
                    )
                }
            },
            divider = {}, // 구분선 제거
        ) {
            uiState.tabs.forEachIndexed { index, title ->
                Tab(
                    selected = uiState.selectedTabIndex == index,
                    onClick = { onTabSelected(index) },
                    modifier = Modifier.height(56.dp),
                ) {
                    Text(
                        text = title,
                        style = AppTextStyles.headerTextRegular,
                        modifier = Modifier.padding(horizontal = 24.dp),
                        color = if (uiState.selectedTabIndex == index) AppColors.black else AppColors.gray1
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
            items(
                items = uiState.products,
                key = { it.id }
            ) { product ->
                ProductItem(
                    product = product,
                    onWishClick = { onWishClick(product) },
                    onItemClick = onProductClick
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ShopScreenPreview() {
    ShopScreen(
        uiState = ShopState(),
        onTabSelected = {},
        onWishClick = {}
    )
}
