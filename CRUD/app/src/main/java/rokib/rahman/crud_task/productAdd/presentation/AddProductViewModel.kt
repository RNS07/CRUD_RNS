package com.rns.accomium.features.product.productAdd.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rns.accomium.AccomiumMainBase.presentation.mvibase.MviViewModel
import com.rns.accomium.features.customer.customerAdd.presentation.AddCustomerAction
import com.rns.accomium.features.customer.customerAdd.presentation.AddCustomerIntent
import com.rns.accomium.features.customer.customerAdd.presentation.AddCustomerViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import javax.inject.Inject


@HiltViewModel
class AddProductViewModel  @Inject constructor(
     val Processor: AddProductProcessor
): ViewModel(),MviViewModel<AddProductIntent, AddProductAction> {


     private var _actionBroadcastChannel = BroadcastChannel<AddProductAction>(Channel.BUFFERED)
     public val _state = MutableStateFlow<AddProductViewState>(AddProductViewState.init())
     val state: StateFlow<AddProductViewState>
          get() = _state
     init {

          _actionBroadcastChannel
               .asFlow().flatMapMerge {
                    Processor.process(it)
               }
                .scan(AddProductViewState.init()) { state, result ->
                    state.reduce(state, result)
                    /* with (stateMachine) { state.getOrDefault() reduce result }*/

               }
               .onEach { state ->


                    _state.value = state
                    //}


               }
               .launchIn(viewModelScope)

     }

     override fun onAction(action: AddProductAction)= _actionBroadcastChannel.offer(action)

     override fun processIntent(Intent: Flow<AddProductIntent>) {
          Intent.onEach {
               Log.d("AddProductprocessIntent", "intent.customer")
               onAction(actionFromIntent(it))
          }.launchIn(viewModelScope)
     }

     override fun actionFromIntent(intent: AddProductIntent): AddProductAction {
          return when (intent) {

               is AddProductIntent.ProductNameChange -> {
                    Log.d("ProductNameChange", "${intent.productName}")
                    AddProductAction.ProductNameChange(intent.productName)
               }
                    is AddProductIntent.AddProduct -> {
                    AddProductAction.AddProduct(intent.product)

               }
               else -> TODO()
          }
     }

}
