package com.example.enuyguncase.presentation.productdetail.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.enuyguncase.presentation.common.components.ShimmerBox
import com.example.enuyguncase.ui.theme.Surface
import com.example.enuyguncase.util.StringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDetailShimmerScreen(
    onBack: () -> Unit
) {
    Scaffold(
        modifier = Modifier.background(Surface),
        containerColor = Surface,
        topBar = {
            CenterAlignedTopAppBar(
                modifier = Modifier.statusBarsPadding(),
                title = {
                    ShimmerBox(
                        modifier = Modifier
                            .fillMaxWidth(0.6f)
                            .height(24.dp),
                        cornerRadius = 4
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = StringResource.commonBack())
                    }
                },
                actions = {
                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = Icons.Filled.FavoriteBorder,
                            contentDescription = StringResource.productDetailAddToFavorites()
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Surface,
                    scrolledContainerColor = Surface
                )
            )
        },
        content = { innerPadding ->
            Box(
                Modifier
                    .fillMaxSize()
                    .background(Surface)
                    .padding(innerPadding)
            ) {
                Column(
                    Modifier
                        .fillMaxSize()
                        .background(Surface)
                        .verticalScroll(rememberScrollState())
                        .padding(bottom = 80.dp, start = 16.dp, end = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    // Image carousel shimmer
                    LazyRow(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(3) { // Show 3 shimmer images
                            Box(
                                modifier = Modifier
                                    .fillParentMaxWidth()
                                    .clip(RoundedCornerShape(12.dp))
                            ) {
                                ShimmerBox(
                                    modifier = Modifier.fillMaxSize(),
                                    cornerRadius = 12
                                )
                                
                                // Discount badge shimmer
                                ShimmerBox(
                                    modifier = Modifier
                                        .align(Alignment.TopEnd)
                                        .padding(8.dp)
                                        .size(36.dp),
                                    cornerRadius = 18
                                )
                            }
                        }
                    }

                    // Page indicators shimmer
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        repeat(3) { idx ->
                            ShimmerBox(
                                Modifier.size(if (idx == 0) 8.dp else 6.dp),
                                cornerRadius = 4
                            )
                            if (idx != 2) Spacer(Modifier.width(4.dp))
                        }
                    }

                    Spacer(Modifier.height(16.dp))

                    // Title shimmer
                    ShimmerBox(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(32.dp),
                        cornerRadius = 4
                    )

                    // Description shimmer
                    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                        ShimmerBox(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(16.dp),
                            cornerRadius = 4
                        )
                        ShimmerBox(
                            modifier = Modifier
                                .fillMaxWidth(0.9f)
                                .height(16.dp),
                            cornerRadius = 4
                        )
                        ShimmerBox(
                            modifier = Modifier
                                .fillMaxWidth(0.7f)
                                .height(16.dp),
                            cornerRadius = 4
                        )
                    }

                    Spacer(Modifier.height(12.dp))

                    // Price shimmer
                    Row(
                        Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        ShimmerBox(
                            modifier = Modifier
                                .fillMaxWidth(0.4f)
                                .height(28.dp),
                            cornerRadius = 4
                        )
                        ShimmerBox(
                            modifier = Modifier
                                .fillMaxWidth(0.3f)
                                .height(28.dp),
                            cornerRadius = 4
                        )
                    }

                    Row(
                        Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(24.dp)
                    ) {
                        ShimmerBox(
                            modifier = Modifier
                                .fillMaxWidth(0.3f)
                                .height(16.dp),
                            cornerRadius = 4
                        )
                        ShimmerBox(
                            modifier = Modifier
                                .fillMaxWidth(0.3f)
                                .height(16.dp),
                            cornerRadius = 4
                        )
                    }

                    Spacer(Modifier.height(16.dp))

                    ShimmerBox(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(72.dp),
                        cornerRadius = 12
                    )
                }
            }
        }
    )
} 