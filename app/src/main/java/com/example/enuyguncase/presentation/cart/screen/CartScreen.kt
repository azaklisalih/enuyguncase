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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.enuyguncase.R
import com.example.enuyguncase.domain.model.CartItem
import com.example.enuyguncase.ui.theme.Black
import com.example.enuyguncase.ui.theme.CardColor
import com.example.enuyguncase.ui.theme.LightGray
import com.example.enuyguncase.ui.theme.MediumGray
import com.example.enuyguncase.ui.theme.Surface
import com.example.enuyguncase.util.StringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen(
    cartItems: List<CartItem>,
    onIncrease: (productId: Int) -> Unit,
    onDecrease: (productId: Int) -> Unit,
    onRemove: (productId: Int) -> Unit,
    onCheckout: () -> Unit,
    isLoading: Boolean = false
) {
    if (isLoading) {
        CartShimmerScreen()
        return
    }

    Scaffold(
        modifier = Modifier.background(Surface),
        topBar = {
            CenterAlignedTopAppBar(title = { 
                Text(
                    StringResource.cartTitle(),
                    style = MaterialTheme.typography.titleLarge
                ) 
            },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(Surface))
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
                items(cartItems, key = { it.productId }) { item ->
                    BasketItemRow(
                        cartItem = item,
                        onIncrease = { onIncrease(item.productId) },
                        onDecrease = { onDecrease(item.productId) },
                        onRemove = { onRemove(item.productId) }
                    )
                }
            }

            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                val originalPriceSum = cartItems.sumOf { it.price * it.quantity }
                val discountSum = cartItems.sumOf { (it.price - it.discountPrice) * it.quantity }
                val totalSum = cartItems.sumOf { it.discountPrice * it.quantity }

                SummaryRow(StringResource.cartPriceLabel(), originalPriceSum)
                SummaryRow(StringResource.cartDiscountLabel(), discountSum)
                SummaryRow(StringResource.cartTotalLabel(), totalSum)

                Spacer(Modifier.height(10.dp))


                Button(
                    onClick = onCheckout,
                    colors = ButtonColors(containerColor = MediumGray, contentColor = MediumGray, disabledContainerColor = MediumGray, disabledContentColor = MediumGray),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(
                    StringResource.cartCheckout(),
                    style = MaterialTheme.typography.labelLarge,
                        color = Black
                )
                }
            }
        }
    }
}

@Composable
private fun SummaryRow(label: String, amount: Double) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(label, style = MaterialTheme.typography.titleSmall)
        Text(
            text = StringResource.cartPriceFormat(amount),
            style = MaterialTheme.typography.titleSmall
        )
    }
}

@Composable
private fun BasketItemRow(
    cartItem: CartItem,
    onIncrease: () -> Unit,
    onDecrease: () -> Unit,
    onRemove: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = CardColor
        )
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            AsyncImage(
                model = cartItem.thumbnail.takeIf { it.isNotBlank() },
                contentDescription = cartItem.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(60.dp)
                    .clip(RoundedCornerShape(8.dp))
            )

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = cartItem.title,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 1
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = StringResource.cartDiscountPriceFormat(cartItem.discountPrice),
                        style = MaterialTheme.typography.titleSmall
                    )
                    Spacer(Modifier.width(8.dp))
                    Text(
                        text = StringResource.cartPriceFormat(cartItem.price),
                        style = MaterialTheme.typography.labelSmall.copy(
                            textDecoration = TextDecoration.LineThrough,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                        )
                    )
                }
            }

            // Quantity controls - compact with square boxes
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Card(
                    modifier = Modifier.size(32.dp),
                    shape = RoundedCornerShape(4.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = if (cartItem.quantity > 1) LightGray else LightGray.copy(alpha = 0.3f)
                    )
                ) {
                    IconButton(
                        onClick = onDecrease,
                        modifier = Modifier.fillMaxSize(),
                        enabled = cartItem.quantity > 1
                    ) {
                        Text(
                            "-", 
                            style = MaterialTheme.typography.titleSmall,
                            color = if (cartItem.quantity > 1) MaterialTheme.colorScheme.onSurface else LightGray.copy(alpha = 0.3f)
                        )
                    }
                }
                
                Text(
                    text = cartItem.quantity.toString(),
                    style = MaterialTheme.typography.titleSmall,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
                
                Card(
                    modifier = Modifier.size(32.dp),
                    shape = RoundedCornerShape(4.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = LightGray
                    )
                ) {
                    IconButton(
                        onClick = onIncrease,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Text("+", style = MaterialTheme.typography.titleSmall)
                    }
                }
            }

            IconButton(
                onClick = onRemove,
                modifier = Modifier.size(32.dp)
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_remove),
                    contentDescription = StringResource.cartRemoveItem(),
                    tint = MaterialTheme.colorScheme.error,
                    modifier = Modifier.size(16.dp)
                )
            }
        }
    }
}
