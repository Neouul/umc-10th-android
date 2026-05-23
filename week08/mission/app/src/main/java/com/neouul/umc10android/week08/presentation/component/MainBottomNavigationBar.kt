package com.neouul.umc10android.week08.presentation.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.neouul.umc10android.week08.R
import com.neouul.umc10android.week08.core.routing.Route
import com.neouul.umc10android.week08.ui.AppColors
import com.neouul.umc10android.week08.ui.AppTextStyles

@Composable
fun MainBottomNavigationBar(
    modifier: Modifier = Modifier,
    currentRoute: Route,
    onTabClick: (Route) -> Unit,
) {
    val items = listOf(
        NavigationItem("홈", R.drawable.ic_house_simple, Route.Home("")),
        NavigationItem("구매하기", R.drawable.ic_list_magnifying_glass, Route.Shop),
        NavigationItem("위시리스트", R.drawable.ic_heart_straight, Route.Wish),
        NavigationItem("장바구니", R.drawable.ic_bag_simple, Route.Cart),
        NavigationItem("프로필", R.drawable.ic_user, Route.Profile)
    )

    val colors = NavigationBarItemDefaults.colors(
        selectedIconColor = AppColors.black,
        unselectedIconColor = AppColors.gray1,
        selectedTextColor = AppColors.black,
        unselectedTextColor = AppColors.gray1,
        indicatorColor = Transparent, // 선택 시 생기는 배경 제거
    )


    NavigationBar(
        containerColor = AppColors.white,
        tonalElevation = 0.dp, // Indicator 배경 효과를 제거하고 싶을 때 사용
        modifier = modifier
            .fillMaxWidth()
            .heightIn(61.dp)
            .windowInsetsPadding(NavigationBarDefaults.windowInsets)
            .dropShadow(
                shape = RectangleShape,
                shadow = Shadow(
                    radius = 20.dp,
                    spread = 0.dp,
                    color = AppColors.black.copy(alpha = 0.03f),
                    offset = DpOffset(x = 0.dp, y = (-4).dp)
                )
            )
    ) {
        items.forEach { item ->
            val isSelected = currentRoute::class == item.route::class

            NavigationBarItem(
                selected = isSelected,
                onClick = { onTabClick(item.route) },
                icon = {
                    Icon(
                        painter = painterResource(item.iconRes),
                        contentDescription = item.label,
                    )
                },
                colors = colors,
                label = {
                    Text(
                        text = item.label,
                        style = if (isSelected) {
                            AppTextStyles.smallTextBold.copy(color = AppColors.black)
                        } else {
                            AppTextStyles.smallTextRegular.copy(color = AppColors.gray1)
                        },
                    )
                },
            )
        }
    }
}

private data class NavigationItem(
    val label: String,
    val iconRes: Int,
    val route: Route,
)

@Preview(showBackground = true)
@Composable
fun MainBottomNavigationBarPreview(modifier: Modifier = Modifier) {
    MainBottomNavigationBar(
        currentRoute = Route.Wish,
        onTabClick = {}
    )
}