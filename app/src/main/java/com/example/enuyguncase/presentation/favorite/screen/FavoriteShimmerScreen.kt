package com.example.enuyguncase.presentation.favorite.screen

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
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.enuyguncase.presentation.common.components.ShimmerBox
import com.example.enuyguncase.ui.theme.CardColor
import com.example.enuyguncase.ui.theme.Surface
import com.example.enuyguncase.util.StringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoriteShimmerScreen() {
    Scaffold(
        modifier = Modifier.background(MaterialTheme.colorScheme.surface),
        containerColor = Surface,
        topBar = {
            CenterAlignedTopAppBar(
                title = { 
                    Text(
                        StringResource.favoriteTitle(),
                        style = MaterialTheme.typography.titleLarge
                    ) 
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Surface
                )
            )
        },
        content = { padding ->
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
            ) {
                items(6) { // Show 6 shimmer items
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp),
                        shape = RoundedCornerShape(12.dp),
                        elevation = CardDefaults.cardElevation(4.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = CardColor,
                        )
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(8.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            // Image placeholder
                            ShimmerBox(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(120.dp),
                                cornerRadius = 8
                            )

                            // Title placeholder
                            ShimmerBox(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(20.dp),
                                cornerRadius = 4
                            )

                            // Description placeholder
                            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                                ShimmerBox(
                                    modifier = Modifier
                                        .fillMaxWidth()
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

                            // Price placeholder
                            ShimmerBox(
                                modifier = Modifier
                                    .fillMaxWidth(0.5f)
                                    .height(20.dp),
                                cornerRadius = 4
                            )

                            Spacer(modifier = Modifier.weight(1f))

                            // Add to cart button placeholder
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.End
                            ) {
                                ShimmerBox(
                                    modifier = Modifier.size(36.dp),
                                    cornerRadius = 18
                                )
                            }
                        }
                    }
                }
            }
        }
    )
} 