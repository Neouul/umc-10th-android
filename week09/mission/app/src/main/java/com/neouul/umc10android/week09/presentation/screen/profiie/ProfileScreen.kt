package com.neouul.umc10android.week09.presentation.screen.profiie

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
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
import com.neouul.umc10android.week09.presentation.component.CommonButton
import com.neouul.umc10android.week09.presentation.component.ProfileTabItem
import com.neouul.umc10android.week09.presentation.component.ThickDivider
import com.neouul.umc10android.week09.presentation.component.VerticalDivider
import com.neouul.umc10android.week09.ui.AppColors
import com.neouul.umc10android.week09.ui.AppTextStyles
import com.neouul.umc10android.week09.R

@Composable
fun ProfileScreen(
    uiState: ProfileState = ProfileState(),
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
                    text = "팔로잉 (n)",
                    style = AppTextStyles.mediumTextMedium,
                    color = AppColors.black
                )
                Text(
                    text = "편집",
                    style = AppTextStyles.headerTextRegular.copy(fontSize = 12.sp),
                    color = AppColors.gray1,
                )
            }

            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                item {
                    Spacer(modifier = Modifier.width(18.dp))
                }
                items(5) {
                    Box(
                        modifier = Modifier
                            .size(106.dp)
                            .background(AppColors.gray3),
                    ) {
                        // 프로필 이미지
                    }
                }
                item {
                    Spacer(modifier = Modifier.width(18.dp))
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