package com.neouul.umc10android.week10.presentation.screen.profiie

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.neouul.umc10android.week10.presentation.component.CommonButton
import com.neouul.umc10android.week10.presentation.component.ProfileTabItem
import com.neouul.umc10android.week10.presentation.component.ThickDivider
import com.neouul.umc10android.week10.presentation.component.VerticalDivider
import com.neouul.umc10android.week10.ui.AppColors
import com.neouul.umc10android.week10.ui.AppTextStyles
import com.neouul.umc10android.week10.domain.model.User
import com.neouul.umc10android.week10.R

@Composable
fun ProfileScreen(
    uiState: ProfileState = ProfileState(),
    onUserClick: (User) -> Unit = {},
    onDismissDialog: () -> Unit = {}
) {
    val scrollState = rememberScrollState()

    if (uiState.isLoading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator(color = AppColors.black)
        }
    } else if (uiState.error != null) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(text = uiState.error, color = AppColors.gray1)
        }
    } else {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(AppColors.white)
                .verticalScroll(scrollState)
        ) {
            // 프로필 헤더
            // ... (생략된 헤더 부분)
            Spacer(modifier = Modifier.height(21.dp))
            Box(
                modifier = Modifier
                    .size(84.dp)
                    .clip(CircleShape)
                    .align(Alignment.CenterHorizontally)
                    .background(AppColors.gray3),
                contentAlignment = Alignment.Center
            ) {
                if (uiState.user?.avatarUrl != null) {
                    AsyncImage(
                        model = uiState.user.avatarUrl,
                        contentDescription = "Profile Avatar",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                } else {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_user),
                        contentDescription = null,
                        modifier = Modifier.size(48.dp),
                        tint = AppColors.gray1
                    )
                }
            }

            Spacer(modifier = Modifier.height(30.dp))

            Text(
                text = uiState.user?.nickName ?: "프로필",
                style = AppTextStyles.headerTextMedium.copy(fontSize = 20.sp),
                modifier = Modifier.align(Alignment.CenterHorizontally),
            )

            Spacer(modifier = Modifier.height(30.dp))

            // 프로필 수정 버튼
            CommonButton(
                text = "프로필 수정",
                modifier = Modifier
                    .width(180.dp)
                    .align(Alignment.CenterHorizontally),
                isOutlined = true,
            )

            Spacer(modifier = Modifier.height(43.dp))

            // 주문 패스 이벤트 설정
            ProfileTabs()

            ThickDivider()

            // 나이키 멤버 혜택
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(101.dp)
                    .padding(horizontal = 24.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "나이키 멤버 혜택",
                        style = AppTextStyles.headerTextMedium,
                        color = AppColors.black
                    )
                    Text(
                        text = "0개 사용 가능",
                        style = AppTextStyles.headerTextRegular.copy(fontSize = 12.sp),
                        color = AppColors.gray1
                    )
                }
                Icon(
                    painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = null,
                    modifier = Modifier
                        .size(14.dp)
                        .rotate(180f),
                    tint = AppColors.black
                )
            }

            ThickDivider()

            // 팔로잉 목록
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 28.dp, bottom = 18.dp)
                    .padding(horizontal = 24.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "팔로잉 (${uiState.followingList.size})",
                    style = AppTextStyles.mediumTextMedium,
                    color = AppColors.black
                )
                Text(
                    text = "편집",
                    style = AppTextStyles.headerTextRegular.copy(fontSize = 12.sp),
                    color = AppColors.gray1,
                )
            }

            val pagerState = rememberPagerState(pageCount = { uiState.followingList.size })

            HorizontalPager(
                state = pagerState,
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(horizontal = 18.dp),
                pageSpacing = 6.dp,
                pageSize = PageSize.Fixed(106.dp)
            ) { page ->
                val user = uiState.followingList.getOrNull(page)
                Box(
                    modifier = Modifier
                        .size(106.dp)
                        .background(AppColors.gray3)
                        .clickable { user?.let { onUserClick(it) } },
                    contentAlignment = Alignment.Center
                ) {
                    if (user?.avatarUrl != null) {
                        AsyncImage(
                            model = user.avatarUrl,
                            contentDescription = "Following User Avatar",
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                    } else {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_user),
                            contentDescription = null,
                            modifier = Modifier.size(48.dp),
                            tint = AppColors.gray1
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(36.dp))

            // 회원 가입일
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .background(AppColors.gray5),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "회원 가입일: 2025년 9월",
                    style = AppTextStyles.smallTextRegular.copy(fontSize = 12.sp),
                    color = AppColors.gray1
                )
            }
        }
    }

    if (uiState.selectedUser != null) {
        AlertDialog(
            onDismissRequest = onDismissDialog,
            confirmButton = { },
            text = {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    AsyncImage(
                        model = uiState.selectedUser.avatarUrl,
                        contentDescription = "Selected User Avatar",
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(1f),
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = uiState.selectedUser.nickName,
                        style = AppTextStyles.mediumTextMedium
                    )
                }
            },
            containerColor = AppColors.white
        )
    }
}

@Composable
fun ProfileTabs() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 25.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        ProfileTabItem(
            iconRes = R.drawable.ic_archive,
            label = "주문",
            modifier = Modifier.weight(1f)
        )
        VerticalDivider()
        ProfileTabItem(
            iconRes = R.drawable.ic_identificationic_card,
            label = "패스",
            modifier = Modifier.weight(1f)
        )
        VerticalDivider()
        ProfileTabItem(
            iconRes = R.drawable.ic_calendar_blank,
            label = "이벤트",
            modifier = Modifier.weight(1f)
        )
        VerticalDivider()
        ProfileTabItem(
            iconRes = R.drawable.ic_gear,
            label = "설정",
            modifier = Modifier.weight(1f)
        )
    }
}


@Preview
@Composable
private fun ProfileScreenPreview() {
    ProfileScreen()
}