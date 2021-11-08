package com.rns.accomium.features.product.productAdd.presentation.ui



import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.rns.accomium.AccomiumMainBase.domain.model.InputData
import com.rns.accomium.AccomiumMainBase.presentation.ui.theme.View.DetailsInputCard
import com.rns.accomium.AccomiumMainBase.presentation.ui.theme.View.InputTextField
import com.rns.accomium.AccomiumMainBase.presentation.ui.theme.View.StandardTextField
import com.rns.accomium.R
import com.rns.accomium.data.product.model.Product
import com.rns.accomium.features.customer.customerAdd.presentation.AddCustomerIntent
import com.rns.accomium.features.product.productAdd.presentation.AddProductIntent
import com.rns.accomium.features.product.productAdd.presentation.AddProductViewModel
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.launch

@Composable
fun AddProduct(navController: NavController, addProductViewModel: AddProductViewModel = hiltViewModel()) {


    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val intent = BroadcastChannel<AddProductIntent>(Channel.BUFFERED)
    val state = addProductViewModel.state.collectAsState()
    val uiState = state.value


    Column(modifier = Modifier
        .fillMaxHeight(1f)
        .background(Color(0xBFBBDEFB))) {
        TopAppBar(
            { Text(text = "Add Product/Service") },
            navigationIcon = {
                IconButton(onClick = {
                    navController.navigate("ListProduct")
                }) {
                    Icon(Icons.Filled.ArrowBack, contentDescription = null)
                }
            },
            backgroundColor = Color(0xFFBBDEFB)
        )
        //productType()

        Card(
            modifier = Modifier
                .padding(0.dp)
                .fillMaxWidth(),
            border = BorderStroke(2.dp, Color(224, 224, 224))

        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth(1f),
                horizontalArrangement = Arrangement.SpaceBetween

            ) {

                Text(
                    text = "Product/Service name",
                    fontWeight = FontWeight.Normal,
                    style = TextStyle(fontSize = 15.sp),
                    color = Color.Black,
                    modifier = Modifier.padding(10.dp)
                )
                InputTextField(
                    text = uiState.producatandServiceName.value,
                    "Product/Service name",
                    onValueChange = {
                        uiState.producatandServiceName.value =it
                        intent.offer(AddProductIntent.ProductNameChange(it))
                        Log.d("ProductNameChange", "${it}")
                        // AddCustomerIntent.CreateCustomer(customer = mutableSetOf(Customer(CustomerName = it)))
                    }
                )

            }
        }

        Card(
            modifier = Modifier
                .padding(0.dp)
                .fillMaxWidth(),
            border = BorderStroke(2.dp, Color(224, 224, 224))

        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth(1f),
                horizontalArrangement = Arrangement.SpaceBetween

            ) {

                Text(
                    text = "Product/Service Code",
                    fontWeight = FontWeight.Normal,
                    style = TextStyle(fontSize = 15.sp),
                    color = Color.Black,
                    modifier = Modifier.padding(10.dp)
                )
                InputTextField(
                    text = uiState.producatandServiceCode.value,
                    "Product/Service Code",
                    onValueChange = {
                        uiState.producatandServiceCode.value = it
                        // AddCustomerIntent.CreateCustomer(customer = mutableSetOf(Customer(CustomerName = it)))
                    }
                )

            }
        }

        Card(
            modifier = Modifier
                .padding(0.dp)
                .fillMaxWidth(),
            border = BorderStroke(2.dp, Color(224, 224, 224))

        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth(1f),
                horizontalArrangement = Arrangement.SpaceBetween

            ) {

                Text(
                    text = "Measurement Unit",
                    fontWeight = FontWeight.Normal,
                    style = TextStyle(fontSize = 15.sp),
                    color = Color.Black,
                    modifier = Modifier.padding(10.dp)
                )
                InputTextField(
                    text = uiState.measurementUnit.value,
                    "Measurement Unit",
                    onValueChange = {
                        uiState.measurementUnit.value = it
                        //AddCustomerIntent.CreateCustomer(customer = mutableSetOf(Customer(CustomerName = it)))
                    }
                )

            }
        }

        Card(
            modifier = Modifier
                .padding(0.dp)
                .fillMaxWidth(),
            border = BorderStroke(2.dp, Color(224, 224, 224))

        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth(1f),
                horizontalArrangement = Arrangement.SpaceBetween

            ) {

                Text(
                    text = "Sale Rate",
                    fontWeight = FontWeight.Normal,
                    style = TextStyle(fontSize = 15.sp),
                    color = Color.Black,
                    modifier = Modifier.padding(10.dp)
                )
                InputTextField(
                    text = uiState.saleRate.value,
                    "Sale Rate",
                    onValueChange = {
                        uiState.saleRate.value = it
                        // AddCustomerIntent.CreateCustomer(customer = mutableSetOf(Customer(CustomerName = it)))
                    }
                )

            }
        }

        Card(
            modifier = Modifier
                .padding(0.dp)
                .fillMaxWidth(),
            border = BorderStroke(2.dp, Color(224, 224, 224))

        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth(1f),
                horizontalArrangement = Arrangement.SpaceBetween

            ) {

                Text(
                    text = "Purchase Rate",
                    fontWeight = FontWeight.Normal,
                    style = TextStyle(fontSize = 15.sp),
                    color = Color.Black,
                    modifier = Modifier.padding(10.dp)
                )
                InputTextField(
                    text = uiState.purchaseRate.value,
                    "Purchase Rate",
                    onValueChange = {
                        uiState.purchaseRate.value = it
                        // AddCustomerIntent.CreateCustomer(customer = mutableSetOf(Customer(CustomerName = it)))
                    }
                )

            }
        }

        Card(
            modifier = Modifier
                .padding(0.dp)
                .fillMaxWidth(),
            border = BorderStroke(2.dp, Color(224, 224, 224))

        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth(1f),
                horizontalArrangement = Arrangement.SpaceBetween

            ) {

                Text(
                    text = "Description",
                    fontWeight = FontWeight.Normal,
                    style = TextStyle(fontSize = 15.sp),
                    color = Color.Black,
                    modifier = Modifier.padding(10.dp)
                )
                InputTextField(
                    text = uiState.description.value,
                    "Description",
                    onValueChange = {
                        uiState.description.value = it
                        // AddCustomerIntent.CreateCustomer(customer = mutableSetOf(Customer(CustomerName = it)))
                    }
                )

            }
        }

        Spacer(modifier = Modifier.padding(25.dp))

        // cAddProduct(listItem = listItemViewModelAddProduct, navController = navController,)
        //Button
        //Save
        OutlinedButton(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .background(Color(0xFFE3F2FD)),
            onClick = {
                      val product = Product( productServicename = uiState.producatandServiceName.value, productServiceCode = uiState.producatandServiceCode.value,
                      measurementUnit = uiState.measurementUnit.value, saleRate = uiState.saleRate.value,purchaseRate = uiState.purchaseRate.value,
                      description = uiState.description.value)
                Log.d("AddProduct", "$product")
                coroutineScope.launch{
                    Log.d("AddCustomer","LaunchedEffect")

                    intent.offer(AddProductIntent.AddProduct(product))



                }
                addProductViewModel.processIntent(intent.asFlow())


          when {

              !uiState.isLoading && (uiState.error == null) -> navController.navigate("ListProduct")
              uiState.error != null ->  Toast.makeText(context,"Error", Toast.LENGTH_LONG).show()
              uiState.isLoading -> Toast.makeText(context,"Loading", Toast.LENGTH_LONG).show()
          }
                      },
            // Assign reference "button" to the Button composable
            // and constrain it to the top of the ConstraintLayout

        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
            ) { Text(text = "Save", color = Color(0xFF2196F3), fontSize = 15.sp) }
        }
        //Cancel
        OutlinedButton(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF2196F3))
                .height(50.dp),
            onClick = { navController.navigate("ListProduct") },
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
            ) { Text(text = "Cancel", color = Color.Red, fontSize = 15.sp) }
        }
    }
}




@Composable
fun cAddProduct(
    listItem: List<InputData>,

    navController: NavController,
) {
    LazyColumn {
        itemsIndexed(items = listItem) { index, item ->
            //Spacer(modifier = Modifier.padding(0.5.dp))
            DetailsInputCard(inputData = item, onclick = {
                //navController.navigate(item.listItemDetailsNavRoute)
                //navController.navigate("NotDoneYet")
            })
            Spacer(modifier = Modifier.padding(0.5.dp))


        }
    }
}
/*
class AddProductViewModel(): ViewModel() {
    val itemsList: MutableLiveData<List<InputData>> by lazy {
        MutableLiveData<List<InputData>>()
    }

    private var itemList: ArrayList<InputData>

    init {
        itemList = ArrayList()
        getitemList()
    }

    private fun getitemList() {
        fechList()
        itemsList.value = itemList
    }

    private fun fechList() {
        itemList.add(InputData("Product/Service Name",""))
        itemList.add(InputData("Product/Service Id","hello"))
        itemList.add(InputData("Measurement Unit","hello"))
        itemList.add(InputData("Sale Rate","hello"))
        itemList.add(InputData("Purchase Rate","hello"))
        itemList.add(InputData("Description","hello"))


    }

}*/