package com.example.enuyguncase.presentation.cart.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
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
import com.example.enuyguncase.ui.theme.Surface
import com.example.enuyguncase.util.StringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartShimmerScreen() {
    Scaffold(
        modifier = Modifier.background(Surface),
        topBar = {
            CenterAlignedTopAppBar(
                title = { 
                    Text(
                        StringResource.cartTitle(),
                        style = MaterialTheme.typography.titleLarge
                    ) 
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(Surface)
            )
        }
    ) { innerPadding ->
        Column(
            Modifier
                .background(Surface)
                .fillMaxSize()
                .padding(innerPadding)
                .padding(bottom = 80.dp)
        ) {
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(5) {
                    CartItemShimmer()
                }
            }

            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                repeat(3) {
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        ShimmerBox(
                            modifier = Modifier
                                .width(80.dp)
                                .height(16.dp),
                            cornerRadius = 4
                        )
                        ShimmerBox(
                            modifier = Modifier
                                .width(60.dp)
                                .height(16.dp),
                            cornerRadius = 4
                        )
                    }
                }

                Spacer(Modifier.height(10.dp))

                ShimmerBox(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    cornerRadius = 12
                )
            }
        }
    }
}

@Composable
private fun CartItemShimmer() {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        ShimmerBox(
            modifier = Modifier
                .size(60.dp),
            cornerRadius = 8
        )

        Column(modifier = Modifier.weight(1f)) {
            ShimmerBox(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(20.dp),
                cornerRadius = 4
            )
            Spacer(Modifier.height(8.dp))
            
            Row(verticalAlignment = Alignment.CenterVertically) {
                ShimmerBox(
                    modifier = Modifier
                        .width(60.dp)
                        .height(16.dp),
                    cornerRadius = 4
                )
                Spacer(Modifier.width(8.dp))
                ShimmerBox(
                    modifier = Modifier
                        .width(50.dp)
                        .height(14.dp),
                    cornerRadius = 4
                )
            }
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            ShimmerBox(
                modifier = Modifier
                    .size(32.dp),
                cornerRadius = 16
            )
            Spacer(Modifier.width(8.dp))
            ShimmerBox(
                modifier = Modifier
                    .width(20.dp)
                    .height(20.dp),
                cornerRadius = 4
            )
            Spacer(Modifier.width(8.dp))
            ShimmerBox(
                modifier = Modifier
                    .size(32.dp),
                cornerRadius = 16
            )
            Spacer(Modifier.width(8.dp))
            ShimmerBox(
                modifier = Modifier
                    .size(32.dp),
                cornerRadius = 16
            )
        }
    }
} 