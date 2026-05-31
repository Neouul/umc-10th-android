package com.neouul.umc10android.week09.presentation.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.neouul.umc10android.week09.R
import com.neouul.umc10android.week09.ui.AppColors
import com.neouul.umc10android.week09.ui.AppTextStyles

@Composable
fun CommonButton(
    text: String,
    icon: Int? = null,
    modifier: Modifier = Modifier,
    isOutlined: Boolean = false,
    onClick: () -> Unit = {},
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isOutlined) AppColors.white else AppColors.black, // 배경색
            contentColor = if (isOutlined) AppColors.black else AppColors.white  // 텍스트색
        ),
        shape = RoundedCornerShape(100.dp),
        border = if (isOutlined) BorderStroke(1.dp, AppColors.gray4) else null,
        modifier = modifier
            .fillMaxWidth()
            .height(51.dp)
    ) {
        Row {
            Text(
                text = text,
                style = AppTextStyles.headerTextMedium,
            )

            if (icon != null) {
                Spacer(modifier = Modifier.width(10.dp))

                Icon(
                    modifier = Modifier.size(24.dp),
                    painter = painterResource(id = icon),
                    contentDescription = null,
                )
            }
        }
    }
}

@Preview
@Composable
private fun CommonButtonPreview() {
    CommonButton(
        "filled"
    )
}

@Preview(showBackground = true)
@Composable
private fun OutlinedCommonButtonPreview() {
    Column {
        CommonButton(
            text = "outlined",
            isOutlined = true
        )

        CommonButton(
            text = "outlined icon",
            icon = R.drawable.ic_house_simple,
            isOutlined = true,
        )
    }
}