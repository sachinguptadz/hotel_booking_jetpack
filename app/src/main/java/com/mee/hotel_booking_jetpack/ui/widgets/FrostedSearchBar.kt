package com.mee.hotel_booking_jetpack.ui.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mee.hotel_booking_jetpack.ui.theme.OutfitFontFamily

@Composable
fun FrostedSearchBar(
    value: String,
    onValueChange: (String) -> Unit
) {
    val shape = RoundedCornerShape(40.dp)

    Box(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 12.dp)
            .fillMaxWidth()
            .height(48.dp)
    ) {

        Box(
            modifier = Modifier
                .matchParentSize()
                .clip(shape)
                .graphicsLayer {
                    compositingStrategy = CompositingStrategy.Offscreen
                }
                .blur(25.dp)
                .background(
                    Brush.verticalGradient(
                        listOf(
                            Color.White.copy(alpha = 0.12f),
                            Color.White.copy(alpha = 0.05f)
                        )
                    )
                )
                .border(1.dp, Color.White.copy(alpha = 0.15f), shape)
        )


        Row(
            modifier = Modifier
                .matchParentSize()
                .clip(shape)
                .padding(horizontal = 16.dp)
                .align(Alignment.CenterStart),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search",
                tint = Color.White.copy(alpha = 0.85f),
                modifier = Modifier.size(20.dp)
            )

            Spacer(Modifier.width(10.dp))

            BasicTextField(
                value = value,
                onValueChange = onValueChange,
                singleLine = true,
                textStyle = TextStyle(
                    color = Color.White,
                    fontSize = 16.sp,
                    fontFamily = OutfitFontFamily
                ),
                decorationBox = { inner ->
                    if (value.isEmpty()) {
                        Text(
                            text = "Search resorts...",
                            color = Color.White.copy(alpha = 0.55f),
                            fontSize = 16.sp,
                            fontFamily = OutfitFontFamily
                        )
                    }
                    inner()
                },
                modifier = Modifier.weight(1f)
            )

            Spacer(Modifier.width(10.dp))

            Icon(
                imageVector = Icons.Default.Mic,
                contentDescription = "Voice",
                tint = Color.White.copy(alpha = 0.85f),
                modifier = Modifier
                    .size(22.dp)
                    .clickable { }
            )
        }
    }
}