package com.leoevg.san_dinner.presentation.screen.after

import androidx.lifecycle.ViewModel
import com.leoevg.san_dinner.data.manager.SharedPrefManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class AfterScreenViewModel @Inject constructor(
    private val sharedPrefManager: SharedPrefManager
) : ViewModel() {

    private val _state = MutableStateFlow(AfterScreenState())
    val state: StateFlow<AfterScreenState> = _state.asStateFlow()

    init {
        loadOrderData()
    }

    private fun loadOrderData() {
        _state.value = AfterScreenState(
            orderData = OrderData(
                firstName = sharedPrefManager.firstName,
                // Assuming lastName is not stored or part of firstName for now
                lastName = "", 
                employeeId = sharedPrefManager.workerID,
                mainDish = sharedPrefManager.lastOrderMainDish,
                sideDish = sharedPrefManager.lastOrderSideDish
            ),
            language = sharedPrefManager.language
        )
    }
}
