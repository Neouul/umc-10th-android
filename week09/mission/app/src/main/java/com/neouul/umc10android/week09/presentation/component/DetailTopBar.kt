package com.neouul.umc10android.week09.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.neouul.umc10android.week09.R
import com.neouul.umc10android.week09.ui.AppColors
import com.neouul.umc10android.week09.ui.AppTextStyles

@Composable
fun DetailTopBar(
    title: String,
    onBackClick: () -> Unit = {},
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(51.dp)
            .background(AppColors.white)
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_back),
            contentDescription = "뒤로가기 아이콘",
            tint = AppColors.black,
            modifier = Modifier
                .size(24.dp)
                .clickable { onBackClick() }
        )

        Text(
            text = title,
            style = AppTextStyles.headerTextRegular.copy(fontWeight = FontWeight.Medium),
            textAlign = TextAlign.Center,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 64.dp)
        )

        Icon(
            painter = painterResource(id = R.drawable.ic_magnifying_glass),
            contentDescription = "검색하기 아이콘",
            tint = AppColors.black,
            modifier = Modifier
                .size(24.dp)
                .clickable { /* Search action */ }
        )
    }
}

@Preview
@Composable
private fun DetailTopBarPreview() {
    DetailTopBar(
        title = "Nike Air Force 1 '07",
    )
}