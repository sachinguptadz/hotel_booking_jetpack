package com.mee.hotel_booking_jetpack.ui.screens

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import com.mee.hotel_booking_jetpack.ui.theme.AppGradients
import com.mee.hotel_booking_jetpack.ui.widgets.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun HomeExploreScreen(onCardClick: (Map<String, String>) -> Unit = {}) {
    val cards = listOf(
        mapOf(
            "city" to "Ocean Bliss Resort, Maldives",
            "cost" to "$780 USD / night",
            "distance" to "Beachfront • 50 m",
            "available" to "Dec 10 - 15",
            "rating" to "4.9 (2,210)",
            "image" to "https://images.unsplash.com/photo-1522708323590-d24dbb6b0267?auto=format&fit=crop&w=1000&q=80"
        ),
        mapOf(
            "city" to "Emerald Lagoon Spa, Bora Bora",
            "cost" to "$940 USD / night",
            "distance" to "Overwater Villa • 20 m",
            "available" to "Dec 20 - 25",
            "rating" to "5.0 (3,420)",
            "image" to "https://images.unsplash.com/photo-1505691938895-1758d7feb511?auto=format&fit=crop&w=1000&q=80"
        ),
        mapOf(
            "city" to "Golden Sands Retreat, Thailand",
            "cost" to "$460 USD / night",
            "distance" to "2 km from Patong Beach",
            "available" to "Nov 28 - Dec 3",
            "rating" to "4.8 (1,850)",
            "image" to "https://images.unsplash.com/photo-1522708323590-d24dbb6b0267?auto=format&fit=crop&w=1000&q=80"
        ),
        mapOf(
            "city" to "Palm Serenity Resort, Bali",
            "cost" to "$520 USD / night",
            "distance" to "Beachfront • 150 m",
            "available" to "Dec 1 - 5",
            "rating" to "4.9 (2,910)",
            "image" to "https://images.unsplash.com/photo-1505691938895-1758d7feb511?auto=format&fit=crop&w=1000&q=80"
        ),
        mapOf(
            "city" to "Azure Coast Villas, Santorini",
            "cost" to "$740 USD / night",
            "distance" to "Sea View • 120 m",
            "available" to "Dec 15 - 20",
            "rating" to "5.0 (2,140)",
            "image" to "https://images.unsplash.com/photo-1522708323590-d24dbb6b0267?auto=format&fit=crop&w=1000&q=80"
        )
    )

    var searchQuery by remember { mutableStateOf("") }
    var visible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) { visible = true }

    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .background(AppGradients.DarkBackground)
    ) {
        val cardHeight = maxHeight * 0.42f

        Column(
            modifier = Modifier
                .fillMaxSize()
                .systemBarsPadding()
        ) {
            FrostedSearchBar(value = searchQuery, onValueChange = { searchQuery = it })

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(bottom = 80.dp)
            ) {
                val filtered = cards.filter { it["city"]!!.contains(searchQuery, ignoreCase = true) }

                itemsIndexed(filtered) { index, card ->
                    val alpha = remember { Animatable(0f) }
                    val offsetY = remember { Animatable(40f) }

                    LaunchedEffect(visible) {
                        if (visible) {
                            delay(index * 150L)
                            launch { alpha.animateTo(1f, tween(600, easing = LinearOutSlowInEasing)) }
                            launch { offsetY.animateTo(0f, tween(700, easing = FastOutSlowInEasing)) }
                        }
                    }

                    Box(
                        Modifier
                            .padding(horizontal = 16.dp, vertical = 14.dp)
                            .graphicsLayer {
                                translationY = offsetY.value
                                this.alpha = alpha.value
                            }
                    ) {
                        ExploreCard(
                            data = card,
                            height = cardHeight,
                            onClick = { onCardClick(card) }
                        )
                    }
                }
            }
        }
    }
}