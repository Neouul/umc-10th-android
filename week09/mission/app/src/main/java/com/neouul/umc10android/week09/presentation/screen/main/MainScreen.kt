package com.neouul.umc10android.week09.presentation.screen.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import com.neouul.umc10android.week09.core.routing.Route
import com.neouul.umc10android.week09.presentation.component.MainBottomNavigationBar

@Composable
fun MainScreen(
    currentDestination: NavDestination?,
    onTabClick: (Route) -> Unit,
    body: @Composable (modifier: Modifier) -> Unit,
    modifier: Modifier = Modifier,
) {
    // 현재 목적지가 MainGraph 내부에 있는지 확인
    val isInMainGraph = currentDestination?.hierarchy?.any {
        it.hasRoute<Route.MainGraph>()
    } == true

    // 현재 탭 경로 확인 (하단바 선택 상태 표시용)
    val currentTab = when {
        currentDestination?.hasRoute<Route.Home>() == true -> Route.Home("")
        currentDestination?.hasRoute<Route.Shop>() == true || currentDestination?.hasRoute<Route.ProductDetail>() == true -> Route.Shop
        currentDestination?.hasRoute<Route.Wish>() == true -> Route.Wish
        currentDestination?.hasRoute<Route.Cart>() == true -> Route.Cart
        currentDestination?.hasRoute<Route.Profile>() == true -> Route.Profile
        else -> Route.Home("")
    }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        bottomBar = {
            // MainGraph 내부에 있을 때만 하단바 노출
            if (isInMainGraph) {
                MainBottomNavigationBar(
                    currentRoute = currentTab,
                    onTabClick = onTabClick
                )
            }
        }
    ) { innerPadding ->
        // 하단바 높이만큼의 패딩이 적용된 Modifier를 body에 주입
        body(Modifier.padding(innerPadding))
    }
}