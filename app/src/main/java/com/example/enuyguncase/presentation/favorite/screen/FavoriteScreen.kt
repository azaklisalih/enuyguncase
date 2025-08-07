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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage
import com.example.enuyguncase.domain.model.Favorite
import com.example.enuyguncase.ui.theme.CardColor
import com.example.enuyguncase.ui.theme.Primary
import com.example.enuyguncase.ui.theme.RedHeart
import com.example.enuyguncase.ui.theme.Surface
import com.example.enuyguncase.util.StringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoriteScreen(
    products: List<Favorite>,
    onRemove: (Int) -> Unit,
    onItemClick: (Int) -> Unit,
    onAddToCart: (Favorite) -> Unit
) {
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
                items(products, key = { it.productId }) { fav ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp),
                        shape = RoundedCornerShape(12.dp),
                        elevation = CardDefaults.cardElevation(4.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = CardColor,
                        ),
                        onClick = { onItemClick(fav.productId) }
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(8.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {

                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(120.dp)
                                    .clip(RoundedCornerShape(8.dp))
                            ) {
                                AsyncImage(
                                    model = fav.thumbnailUrl.takeIf { it.isNotBlank() },
                                    contentDescription = fav.title,
                                    contentScale = ContentScale.Inside,
                                    modifier = Modifier.fillMaxSize()
                                )
                                IconButton(
                                    onClick = { onRemove(fav.productId) },
                                    modifier = Modifier
                                        .size(24.dp)
                                        .align(Alignment.TopEnd)
                                ) {
                                    Icon(
                                        Icons.Filled.Favorite,
                                        contentDescription = null,
                                        tint = RedHeart
                                    )
                                }
                            }

                            Text(
                                text = fav.title,
                                style = MaterialTheme.typography.titleMedium,
                                maxLines = 1
                            )
                            Text(
                                text = fav.description,
                                style = MaterialTheme.typography.bodyMedium,
                                maxLines = 2,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                            Text(
                                text = StringResource.favoritePriceFormat(fav.discountedPrice),
                                style = MaterialTheme.typography.titleMedium
                            )

                            Spacer(modifier = Modifier.weight(1f))

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.End
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(36.dp)
                                        .background(
                                            color = Surface,
                                            shape = CircleShape
                                        ),
                                    contentAlignment = Alignment.Center
                                ) {
                                    IconButton(
                                        onClick = { onAddToCart(fav) },
                                        modifier = Modifier.fillMaxSize()
                                    ) {
                                    Icon(
                                        imageVector = Icons.Filled.Add,
                                        contentDescription = StringResource.productDetailAddToCart(),
                                        tint = Primary,
                                        modifier = Modifier.size(24.dp)
                                    )
                                }
                            }
                            }
                        }
                    }
                }


            }
        }
    )
}
