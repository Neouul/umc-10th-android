package com.neouul.umc10android.week08.core.routing

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.neouul.umc10android.week08.presentation.screen.cart.CartScreen
import com.neouul.umc10android.week08.presentation.screen.home.HomeRoot
import com.neouul.umc10android.week08.presentation.screen.main.MainScreen
import com.neouul.umc10android.week08.presentation.screen.profiie.ProfileScreen
import com.neouul.umc10android.week08.presentation.screen.shop.ShopRoot
import com.neouul.umc10android.week08.presentation.screen.wish.WishRoot
import androidx.navigation.toRoute
import com.neouul.umc10android.week08.presentation.screen.splash.SplashRoot
import com.neouul.umc10android.week08.presentation.screen.detail.DetailRoot

@Composable
fun NavigationRoot(
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        navController = navController,
        startDestination = Route.Splash,
    ) {
        composable<Route.Splash> {
            SplashRoot(
                onNavigateToMain = { title ->
                    navController.navigate(Route.Main(title = title)) {
                        popUpTo(Route.Splash) { inclusive = true }
                    }
                }
            )
        }
        composable<Route.SignIn> {
            // SignInScreen()
        }
        composable<Route.SignUp> {
            // SignUpScreen()
        }
        composable<Route.Main> { backStackEntry ->
            val mainRoute: Route.Main = backStackEntry.toRoute()
            val passedTitle = mainRoute.title

            // MainScreen 내부 탭 전용의 새로운 NavController
            val mainNavController = rememberNavController()
            val mainNavBackStackEntry by mainNavController.currentBackStackEntryAsState()
            val mainCurrentDestination = mainNavBackStackEntry?.destination

            // 공통 네비게이션 로직
            val onTabClick: (Route) -> Unit = { route ->
                mainNavController.navigate(route) {
                    popUpTo(mainNavController.graph.findStartDestination().id) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            }

            MainScreen(
                currentDestination = mainCurrentDestination,
                onTabClick = onTabClick,
                body = { safeModifier ->
                    // 하단바 패딩이 적용된 modifier를 NavHost에 설정
                    NavHost(
                        navController = mainNavController,
                        startDestination = Route.MainGraph,
                        modifier = safeModifier
                    ) {
                        navigation<Route.MainGraph>(
                            startDestination = Route.Home(title = passedTitle)
                        ) {
                            composable<Route.Home> { backStackEntry ->
                                val homeRoute: Route.Home = backStackEntry.toRoute()
                                HomeRoot(
                                    title = homeRoute.title,
                                    onNavigateToDetail = { product ->
                                        mainNavController.navigate(Route.ProductDetail(productId = product.id))
                                    }
                                )
                            }
                            composable<Route.Shop> {
                                ShopRoot(
                                    onNavigateToDetail = { product ->
                                        mainNavController.navigate(Route.ProductDetail(productId = product.id))
                                    }
                                )
                            }
                            composable<Route.Wish> {
                                WishRoot(
                                    onNavigateToDetail = { product ->
                                        mainNavController.navigate(Route.ProductDetail(productId = product.id))
                                    }
                                )
                            }
                            composable<Route.Cart> {
                                CartScreen(
                                    onNavigateToShop = {
                                        onTabClick(Route.Shop)
                                    }
                                )
                            }
                            composable<Route.Profile> {
                                ProfileScreen()
                            }
                            composable<Route.ProductDetail> {
                                DetailRoot(
                                    onBackClick = {
                                        mainNavController.popBackStack()
                                    }
                                )
                            }
                        }
                    }
                }
            )
        }
    }
}