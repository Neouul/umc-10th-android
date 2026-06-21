package com.neouul.umc10android.week10.ui

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp

object AppTextStyles {

    // 10px
    val smallTextRegular = TextStyle(
        fontFamily = AppFonts.NotoSans,
        fontWeight = FontWeight.Normal,
        fontSize = 10.sp,
        letterSpacing = (-0.02).em,
    )

    val smallTextBold = TextStyle(
        fontFamily = AppFonts.NotoSans,
        fontWeight = FontWeight.Bold,
        fontSize = 10.sp,
        letterSpacing = (-0.02).em,
    )

    // 14px
    val mediumTextMedium = TextStyle(
        fontFamily = AppFonts.NotoSans,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        letterSpacing = (-0.01).em,
        lineHeight = 1.2.em,
    )

    val mediumTextRegular = TextStyle(
        fontFamily = AppFonts.NotoSans,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        letterSpacing = (-0.01).em,
        lineHeight = 1.2.em,
    )

    // 16px
    val headerTextMedium = TextStyle(
        fontFamily = AppFonts.NotoSans,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        lineHeight = 1.2.em,
    )

    val headerTextRegular = TextStyle(
        fontFamily = AppFonts.NotoSans,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        letterSpacing = (-0.025).em,
    )

    val largeTextRegular = TextStyle(
        fontFamily = AppFonts.NotoSans,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        letterSpacing = (-0.025).em,
        lineHeight = 1.5.em,
    )

    // 28px
    val titleTextMedium = TextStyle(
        fontFamily = AppFonts.NotoSans,
        fontWeight = FontWeight.Medium,
        fontSize = 28.sp,
        letterSpacing = (-0.06).em,
        lineHeight = 1.2.em,
    )

}