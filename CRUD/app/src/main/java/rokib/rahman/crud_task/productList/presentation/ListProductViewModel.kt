package com.rns.accomium.features.product.productList.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rns.accomium.AccomiumMainBase.presentation.mvibase.MviViewModel
import com.rns.accomium.features.customer.customerList.presentation.ListCustomerAction
import com.rns.accomium.features.customer.customerList.presentation.ListCustomerIntent
import com.rns.accomium.features.customer.customerList.presentation.ListCustomerViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import javax.inject.Inject


@HiltViewModel
class ListProductViewModel  @Inject constructor(
     val Processor: ListProductProcessor
): ViewModel(),MviViewModel<ListProductIntent, ListProductAction> {

     private var _actionBroadcastChannel = BroadcastChannel<ListProductAction>(Channel.BUFFERED)

     public val _state = MutableStateFlow<ListProductViewState>(ListProductViewState.init())
     val state: StateFlow<ListProductViewState>
          get() = _state

     init {_actionBroadcastChannel
          .asFlow().flatMapMerge {
               Processor.process(it)
          }
          .scan(ListProductViewState.init()) { state, result ->
               state.reduce(state, result)

          }
          .onEach { state ->
               // if(_state.value !is SettingViewState.SetingOptionsState){

               _state.value = state

               Log.d("SettingsUI", "onEach")

          }
          .launchIn(viewModelScope)

     }

     override fun onAction(action: ListProductAction) = _actionBroadcastChannel.offer(action)


     override fun processIntent(Intent: Flow<ListProductIntent>) {
          Intent.onEach {
               onAction(actionFromIntent(it))
          }.launchIn(viewModelScope)
     }

     override fun actionFromIntent(intent: ListProductIntent): ListProductAction {
          return when (intent) {
               is ListProductIntent.LoadAllIntent ->
                    ListProductAction.LoadAllActions

               else -> ListProductAction.NoAllActions
          }
     }
}
