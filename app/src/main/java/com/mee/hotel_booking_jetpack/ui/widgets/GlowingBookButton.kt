package com.mee.hotel_booking_jetpack.ui.widgets

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mee.hotel_booking_jetpack.ui.theme.AppGradients
import com.mee.hotel_booking_jetpack.ui.theme.OutfitFontFamily

@Composable
fun GlowingBookButton(shineOffset: Float = 0f, text: String = "Book Now") {
    val shape = RoundedCornerShape(20.dp)
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .clip(shape)
            .background(AppGradients.BlueHorizontal)
            .border(
                1.dp,
                Brush.linearGradient(
                    listOf(Color.White.copy(alpha = 0.3f), Color.Transparent)
                ),
                shape
            )
            .shadow(
                elevation = 18.dp,
                shape = shape,
                ambientColor = Color(0xFF00E5FF).copy(alpha = 0.35f),
                spotColor = Color(0xFF0072FF).copy(alpha = 0.25f)
            ),
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier.matchParentSize()) {
            drawRect(
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color.White.copy(alpha = 0f),
                        Color.White.copy(alpha = 0.25f),
                        Color.White.copy(alpha = 0f)
                    ),
                    start = Offset(size.width * (shineOffset - 0.15f), 0f),
                    end = Offset(size.width * (shineOffset + 0.15f), size.height)
                ),
                blendMode = BlendMode.Lighten
            )
        }

        Text(
            text = text,
            color = Color.White,
            fontFamily = OutfitFontFamily,
            fontSize = 17.sp
        )
    }
}