package com.rns.accomium.Fragment

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.rns.accomium.AccomiumMainBase.presentation.ui.EmptyUi
import com.rns.accomium.basic.notification.presentation.ui.Loading
import com.rns.accomium.data.customer.model.Customer
import com.rns.accomium.data.product.model.Product
import com.rns.accomium.features.customer.customerList.presentation.ListCustomerIntent
import com.rns.accomium.features.customer.customerList.presentation.ListCustomerViewModel
import com.rns.accomium.features.product.productList.presentation.ListProductIntent
import com.rns.accomium.features.product.productList.presentation.ListProductViewModel
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.asFlow

@Composable
fun ListProduct(
    navController: NavController,
    listProductViewModel: ListProductViewModel = hiltViewModel(),
    ) {
    val loadInitialIntent = BroadcastChannel<ListProductIntent>(Channel.BUFFERED)
    LaunchedEffect(true){

        loadInitialIntent.offer(ListProductIntent.LoadAllIntent)

    }
    listProductViewModel.processIntent(  loadInitialIntent.asFlow())

    val state = listProductViewModel.state.collectAsState()
    val uiState = state.value

    Scaffold(
        topBar = {
            TopAppBar(
                { Text(text = "Product/Service") },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.navigate("Homepage")
                    }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = null)
                    }
                },
                actions = {

                    // RowScope here, so these icons will be placed horizontally

                    IconButton(onClick = {
                        //todo
                    }) {
                        Icon(
                            Icons.Filled.Search,
                            contentDescription = "Localized description"
                        )
                    }

                    IconButton(onClick = {
                        //
                    }) {
                        Icon(
                            Icons.Filled.List,
                            contentDescription = "Localized description"
                        )
                    }

                    IconButton(onClick = {
                        //
                    }) {
                        Icon(Icons.Filled.Refresh, contentDescription = "Localized description")
                    }
                },
                backgroundColor = Color(0xFFBBDEFB)
            )
        },

        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController
                        .navigate("AddProduct")
                },
                backgroundColor = Color(0xFFBBDEFB)
            ) {
                Icon(
                    Icons.Filled.Add,
                    contentDescription = "Localized description",

                    )
            }
        },
        floatingActionButtonPosition = FabPosition.End,

        ) {
        when {

            !uiState.isLoading && (uiState.error == null) -> ProductList(
                navController,uiState.data)
            uiState.error != null ->  Log.d("Hello","hi")
            uiState.empty -> EmptyUi()
            uiState.isLoading -> Loading()
        }
    }
}

@Composable
fun ProductList(navController: NavController, data: List<Product>) {
    Log.d("CustomerList","$data")
    LazyColumn {
        itemsIndexed(items = data) { index, item ->
            Card(
                modifier = Modifier
                    .padding()
                    .fillMaxWidth()
                    .clickable(onClick = {})
            ) {
                Row (modifier = Modifier.background(Color(0xe0000000000))){
                    Surface(
                        modifier = Modifier
                            .size(40.dp)
                            .padding(2.dp, 2.dp)
                        ,color= MaterialTheme.colors.surface.copy(alpha = 0.2f)
                    ) {

                        /*
        // Icon in Settings
                        Image(painter = painterResource(
                            id =listData.listImg),
                            contentDescription = null,
                            modifier = Modifier.height(10.dp),
                            contentScale = ContentScale.Crop
                        )

                         */
                    }
                    Row(
                        modifier = Modifier
                            .padding(5.dp)
                            .fillMaxWidth(1f)
                            .align(Alignment.CenterVertically),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column() {

                            Text(
                                text = item.productServicename,fontWeight = FontWeight.Bold,
                                style = TextStyle(fontSize = 15.sp),color = Color.Black,

                                modifier = Modifier.fillMaxWidth(.5f)
                            )
                            Spacer(modifier = Modifier.padding(3.dp))

                            Text(
                                text = item.productServiceCode,
                                fontWeight = FontWeight.Normal,
                                style = TextStyle(fontSize = 12.sp),color = Color.Black,

                                modifier = Modifier.fillMaxWidth(.5f)
                            )
                            Spacer(modifier = Modifier.padding(3.dp))

                            Text(
                                text = item.description,
                                fontWeight = FontWeight.Normal,
                                style = TextStyle(fontSize = 12.sp),color = Color.Black,

                                modifier = Modifier.fillMaxWidth(.5f)
                            )

                        }

                        
                            Column() {

                                Spacer(modifier = Modifier.padding(10.dp))
                                Text(text = "Purchase Rate USD "+ item.purchaseRate,
                                    style = MaterialTheme.typography.body2,fontSize = 12.sp,
                                    maxLines = 1,overflow = TextOverflow.Ellipsis,
                                    modifier = Modifier.padding(end = 25.dp))
                               Spacer(modifier = Modifier.padding(5.dp))
                                Text(text = "Sale Rate USD "+ item.saleRate,
                                    style = MaterialTheme.typography.body2,fontSize = 12.sp,
                                    maxLines = 1,overflow = TextOverflow.Ellipsis,
                                    modifier = Modifier.padding(end = 25.dp))
                            }
                        
                    }
                }
            }
            Divider()
        }
    }
}