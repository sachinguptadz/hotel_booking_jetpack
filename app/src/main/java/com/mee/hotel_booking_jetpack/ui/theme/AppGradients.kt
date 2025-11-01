package com.mee.hotel_booking_jetpack.ui.theme

import androidx.compose.ui.graphics.Brush

object AppGradients {
    val DarkBackground = Brush.verticalGradient(
        listOf(AppColors.DarkBgStart, AppColors.DarkBgMid, AppColors.DarkBgEnd)
    )

    val BlueHorizontal = Brush.horizontalGradient(
        listOf(AppColors.DeepBlue, AppColors.NeonBlue)
    )

    val Frosted = Brush.linearGradient(
        listOf(AppColors.WhiteTransparent.copy(alpha = 0.8f), AppColors.WhiteTransparent.copy(alpha = 0.02f))
    )
}