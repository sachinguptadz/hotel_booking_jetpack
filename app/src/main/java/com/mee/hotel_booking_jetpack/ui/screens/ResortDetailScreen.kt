package com.mee.hotel_booking_jetpack.ui.screens

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.*
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.*
import coil.compose.rememberAsyncImagePainter
import com.mee.hotel_booking_jetpack.ui.theme.AppColors
import com.mee.hotel_booking_jetpack.ui.theme.OutfitFontFamily
import com.mee.hotel_booking_jetpack.ui.widgets.*
import kotlinx.coroutines.delay

@Composable
fun ResortDetailScreen(
    data: Map<String, String>,
    onBack: () -> Unit = {}
) {
    val imagePainter = rememberAsyncImagePainter(data["image"])
    val scrollState = rememberScrollState()

    val fadeIn = remember { Animatable(0f) }
    val slideY = remember { Animatable(60f) }

    val infiniteAnim = rememberInfiniteTransition(label = "glow")
    val shimmer by infiniteAnim.animateFloat(
        initialValue = -1f, targetValue = 1f,
        animationSpec = infiniteRepeatable(tween(3000, easing = LinearEasing))
    )

    LaunchedEffect(Unit) {
        delay(100)
        fadeIn.animateTo(1f, tween(900, easing = EaseOutQuad))
        slideY.animateTo(0f, tween(900, easing = EaseOutBack))
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(AppColors.DarkBgEnd)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(420.dp)
                .clip(RoundedCornerShape(bottomStart = 40.dp, bottomEnd = 40.dp))
                .graphicsLayer {
                    translationY = slideY.value
                    alpha = fadeIn.value
                }
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
                            listOf(
                                Color.Black.copy(0.1f),
                                Color.Black.copy(0.5f),
                                Color.Black.copy(0.95f)
                            )
                        )
                    )
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
                .statusBarsPadding(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            GlassIconButton(Icons.Default.ArrowBackIosNew, onBack)
            GlassIconButton(Icons.Default.FavoriteBorder) {}
        }


        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(top = 350.dp)
                .graphicsLayer {
                    translationY = slideY.value
                    alpha = fadeIn.value
                }
        ) {
            GlowingAvatar()
            Spacer(Modifier.height(26.dp))


            FloatingGlassCard {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        "Hosted by Sachin",
                        color = Color.White,
                        fontFamily = OutfitFontFamily,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 21.sp
                    )
                    Spacer(Modifier.height(4.dp))
                    Text(
                        "Luxury • Lifestyle",
                        color = Color.White.copy(0.8f),
                        fontFamily = OutfitFontFamily,
                        fontSize = 15.sp
                    )

                    Spacer(Modifier.height(16.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Star, null, tint = Color(0xFF00FFC6), modifier = Modifier.size(18.dp))
                        Spacer(Modifier.width(4.dp))
                        Text("4.6", color = Color.White, fontSize = 14.sp)
                        Spacer(Modifier.width(6.dp))
                        Text("1,773 reviews", color = Color.White.copy(0.7f), fontSize = 13.sp)
                        Spacer(Modifier.width(8.dp))
                        Icon(Icons.Default.Verified, null, tint = Color.White.copy(0.8f), modifier = Modifier.size(16.dp))
                        Spacer(Modifier.width(4.dp))
                        Text("Superhost", color = Color.White.copy(0.7f), fontSize = 13.sp)
                    }

                    Spacer(Modifier.height(20.dp))
                    Row(horizontalArrangement = Arrangement.Center) {
                        Icon(Icons.Default.LocationOn, null, tint = Color.White.copy(0.7f), modifier = Modifier.size(18.dp))
                        Spacer(Modifier.width(6.dp))
                        Text(
                            "10880 Malibu Point, Malibu, California",
                            color = Color.White.copy(0.6f),
                            fontSize = 13.sp,
                            textAlign = TextAlign.Center
                        )
                    }

                    Spacer(Modifier.height(26.dp))
                    GlowingBookButton(shimmer)
                }
            }

            Spacer(Modifier.height(20.dp))


            FloatingGlassCard {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(50.dp)
                            .clip(RoundedCornerShape(50))
                            .background(
                                Brush.linearGradient(
                                    listOf(Color(0xFF00E5FF), Color(0xFF0072FF))
                                )
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(Icons.Default.VerifiedUser, null, tint = Color.White, modifier = Modifier.size(22.dp))
                    }
                    Spacer(Modifier.width(14.dp))
                    Column {
                        Text(
                            "Sachin is a Superhost",
                            color = Color.White,
                            fontFamily = OutfitFontFamily,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 16.sp
                        )
                        Spacer(Modifier.height(6.dp))
                        Text(
                            "Superhosts are experienced, highly rated hosts who are committed to providing great stays for guests.",
                            color = Color.White.copy(0.7f),
                            fontSize = 13.sp,
                            fontFamily = OutfitFontFamily
                        )
                    }
                }
            }

            Spacer(Modifier.height(120.dp))
        }
    }
}