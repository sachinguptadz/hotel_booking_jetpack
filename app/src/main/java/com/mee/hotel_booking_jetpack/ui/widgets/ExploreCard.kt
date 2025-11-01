package com.mee.hotel_booking_jetpack.ui.widgets

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.*
import coil.compose.rememberAsyncImagePainter
import com.mee.hotel_booking_jetpack.ui.theme.AppColors
import com.mee.hotel_booking_jetpack.ui.theme.OutfitFontFamily

@Composable
fun ExploreCard(
    data: Map<String, String>,
    height: Dp,
    onClick: () -> Unit
) {
    val imagePainter = rememberAsyncImagePainter(model = data["image"])
    val shape = RoundedCornerShape(26.dp)
    var isFavorite by remember { mutableStateOf(false) }

    val scale = animateFloatAsState(if (isFavorite) 1.2f else 1f)
    val heartColor by animateColorAsState(
        if (isFavorite) Color.Red else Color.White.copy(alpha = 0.9f)
    )

    Box(
        Modifier
            .clip(shape)
            .clickable { onClick() }
            .shadow(10.dp, shape)
            .height(height)
    ) {

        Image(
            painter = imagePainter,
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )


        Box(
            Modifier
                .matchParentSize()
                .background(
                    Brush.verticalGradient(
                        listOf(Color.Transparent, Color.Black.copy(0.7f), Color.Black.copy(0.9f))
                    )
                )
        )


        Box(
            Modifier
                .align(Alignment.TopStart)
                .padding(16.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(AppColors.DeepBlue)
                .padding(horizontal = 10.dp, vertical = 4.dp)
        ) {
            Text(
                "★ ${data["rating"]}",
                color = Color.White,
                fontFamily = OutfitFontFamily,
                fontWeight = FontWeight.SemiBold,
                fontSize = 13.sp
            )
        }


        Box(
            Modifier
                .align(Alignment.TopEnd)
                .padding(16.dp)
                .size(34.dp)
                .scale(scale.value)
                .clip(CircleShape)
                .background(Color.Black.copy(alpha = 0.3f))
                .border(1.dp, Color.White.copy(alpha = 0.15f), CircleShape)
                .clickable { isFavorite = !isFavorite },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Default.FavoriteBorder,
                contentDescription = null,
                tint = heartColor
            )
        }


        Box(
            Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .height(IntrinsicSize.Min)
                .clip(RoundedCornerShape(20.dp))
                .padding(horizontal = 0.dp, vertical = 0.dp)
        ) {

            Box(
                Modifier
                    .matchParentSize()
                    .graphicsLayer {
                        compositingStrategy = CompositingStrategy.Offscreen
                    }
                    .blur(20.dp)
                    .background(
                        Brush.verticalGradient(
                            listOf(
                                Color.White.copy(alpha = 0.12f),
                                Color.White.copy(alpha = 0.05f)
                            )
                        )
                    )
                    .border(1.dp, Color.White.copy(alpha = 0.2f), RoundedCornerShape(20.dp))
            )


            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 18.dp)
            ) {
                Text(
                    data["city"] ?: "",
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.W600,
                    fontFamily = OutfitFontFamily,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(Modifier.height(10.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    DetailItem(
                        "COST",
                        Icons.Default.AttachMoney,
                        data["cost"]!!,
                        AppColors.NeonBlue,
                        Modifier.weight(1f)
                    )
                    DividerLine()
                    DetailItem(
                        "DISTANCE",
                        Icons.Default.Route,
                        data["distance"]!!,
                        AppColors.Teal,
                        Modifier.weight(1f)
                    )
                    DividerLine()
                    DetailItem(
                        "AVAILABLE",
                        Icons.Default.DateRange,
                        data["available"]!!,
                        AppColors.PurpleGlow,
                        Modifier.weight(1f)
                    )
                }
            }
        }
    }
}