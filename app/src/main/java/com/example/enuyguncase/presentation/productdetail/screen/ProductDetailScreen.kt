import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.example.enuyguncase.ui.theme.Surface
import androidx.compose.foundation.Image
import androidx.compose.animation.core.*
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateFloatAsState
import kotlinx.coroutines.launch
import coil.compose.rememberAsyncImagePainter
import com.example.enuyguncase.domain.model.Product
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import com.example.enuyguncase.presentation.productdetail.ProductDetailUIState
import com.example.enuyguncase.presentation.productdetail.screen.ProductDetailShimmerScreen
import com.example.enuyguncase.ui.theme.ButtonColor
import com.example.enuyguncase.ui.theme.RedHeart
import com.example.enuyguncase.util.StringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDetailScreen(
    uiState: ProductDetailUIState,
    isFavorite: Boolean,
    onBack: () -> Unit,
    onToggleFavorite: () -> Unit,
    onAddToCart: (Product) -> Unit
) {
    if (uiState.isLoading) {
        ProductDetailShimmerScreen(onBack = onBack)
        return
    }

    val product = uiState.product ?: return

    Scaffold(
        modifier = Modifier.background(Surface),
        containerColor = Surface,
        topBar = {
            CenterAlignedTopAppBar(
                modifier = Modifier.statusBarsPadding(),
                title = {
                    Text(
                        product.title,
                        maxLines = 1,
                        style = MaterialTheme.typography.titleLarge
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = StringResource.commonBack()
                        )
                    }
                },
                actions = {
                    IconButton(onClick = onToggleFavorite) {
                        Icon(
                            imageVector = if (isFavorite)
                                Icons.Filled.Favorite
                            else
                                Icons.Outlined.FavoriteBorder,
                            contentDescription = if (isFavorite) StringResource.productDetailRemoveFromFavorites() else StringResource.productDetailAddToFavorites(),
                            tint = if (isFavorite)
                                RedHeart
                            else LocalContentColor.current
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

                val listState = rememberLazyListState()
                val currentPage by remember {
                    derivedStateOf { listState.firstVisibleItemIndex }
                }

                Column(
                    Modifier
                        .fillMaxSize()
                        .background(Surface)
                        .verticalScroll(rememberScrollState())
                        .padding(bottom = 80.dp, start = 16.dp, end = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {

                    LazyRow(
                        state = listState,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(product.images) { imgUrl ->
                            Box(
                                modifier = Modifier
                                    .fillParentMaxWidth()
                                    .clip(RoundedCornerShape(12.dp))
                            ) {

                                Image(
                                    painter = rememberAsyncImagePainter(imgUrl),
                                    contentDescription = product.title,
                                    modifier = Modifier
                                        .fillMaxSize(),
                                    contentScale = ContentScale.Crop
                                )


                                if (product.discountPercentage > 0) {
                                    val discountText =
                                        StringResource.productDetailDiscount(product.discountPercentage.toInt())
                                    Box(
                                        contentAlignment = Alignment.Center,
                                        modifier = Modifier
                                            .align(Alignment.TopEnd)
                                            .padding(8.dp)
                                            .size(36.dp)
                                            .background(
                                                color = Color.Red,
                                                shape = CircleShape
                                            )
                                            .padding(horizontal = 6.dp, vertical = 2.dp)
                                    ) {
                                        Text(
                                            text = discountText,
                                            style = MaterialTheme.typography.bodySmall.copy(color = Color.White)
                                        )
                                    }
                                }
                            }
                        }
                    }


                    Row(
                        Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        product.images.forEachIndexed { idx, _ ->
                            Box(
                                Modifier
                                    .size(if (idx == currentPage) 8.dp else 6.dp)
                                    .background(
                                        color = if (idx == currentPage)
                                            MaterialTheme.colorScheme.primary
                                        else
                                            MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3f),
                                        shape = CircleShape
                                    )
                            )
                            if (idx != product.images.lastIndex) Spacer(Modifier.width(4.dp))
                        }
                    }

                    Spacer(Modifier.height(16.dp))


                    Text(product.title, style = MaterialTheme.typography.headlineSmall)
                    Text(
                        product.description,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.alpha(0.7f)
                    )

                    Spacer(Modifier.height(12.dp))


                    Row(
                        Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(
                            text = StringResource.productDetailOriginalPriceFormat(product.price),
                            style = MaterialTheme.typography.titleLarge.copy(
                                textDecoration = TextDecoration.LineThrough,
                                color = Color.Black.copy(alpha = 0.6f)
                            )
                        )
                        Text(
                            text = StringResource.productDetailPriceFormat(product.discountedPrice),
                            style = MaterialTheme.typography.titleLarge.copy(
                                color = Color.Black
                            )
                        )
                    }


                    Row(
                        Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(24.dp)
                    ) {
                        Text("Stok: ${product.stock}", style = MaterialTheme.typography.labelMedium)
                        Text(
                            "Puan: ${product.rating}",
                            style = MaterialTheme.typography.labelMedium
                        )
                    }

                    Spacer(Modifier.height(16.dp))

                    var showSuccess by remember { mutableStateOf(false) }
                    val scope = rememberCoroutineScope()
                    val interactionSource = remember { MutableInteractionSource() }
                    val isPressedState by interactionSource.collectIsPressedAsState()
                    
                    val scale by animateFloatAsState(
                        targetValue = if (isPressedState) 0.95f else 1f,
                        animationSpec = tween(durationMillis = 150),
                        label = "button_scale"
                    )
                    
                    val buttonColor by animateColorAsState(
                        targetValue = when {
                            showSuccess -> Color(0xFF4CAF50) // Green for success
                            isPressedState -> ButtonColor.copy(alpha = 0.8f)
                            else -> ButtonColor
                        },
                        animationSpec = tween(durationMillis = 300),
                        label = "button_color"
                    )

                    Button(
                        onClick = { 
                            if (!showSuccess) {
                                onAddToCart(product)
                                showSuccess = true
                                scope.launch {
                                    kotlinx.coroutines.delay(2000)
                                    showSuccess = false
                                }
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(72.dp)
                            .align(Alignment.CenterHorizontally)
                            .navigationBarsPadding()
                            .padding(horizontal = 16.dp)
                            .scale(scale)
                            .animateContentSize(
                                animationSpec = spring(
                                    dampingRatio = Spring.DampingRatioMediumBouncy,
                                    stiffness = Spring.StiffnessLow
                                )
                            ),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = buttonColor
                        ),
                        interactionSource = interactionSource
                    ) {
                        ButtonContent(
                            showSuccess = showSuccess,
                            isPressedState = isPressedState
                        )
                    }
                }
            }
        }
    )
}

@Composable
private fun ButtonContent(
    showSuccess: Boolean,
    isPressedState: Boolean
) {
    if (showSuccess) {
        Icon(
            Icons.Filled.ShoppingCart, 
            contentDescription = null,
            modifier = Modifier.scale(1.2f)
        )
        Spacer(Modifier.width(8.dp))
        Text(
            StringResource.productDetailAddedToCart(),
            style = MaterialTheme.typography.labelLarge,
            color = Color.White
        )
    } else {
        Icon(
            Icons.Filled.ShoppingCart, 
            contentDescription = null,
            modifier = Modifier.scale(if (isPressedState) 1.1f else 1f)
        )
        Spacer(Modifier.width(8.dp))
        Text(
            StringResource.productDetailAddToCart(),
            style = MaterialTheme.typography.labelLarge
        )
    }
}
