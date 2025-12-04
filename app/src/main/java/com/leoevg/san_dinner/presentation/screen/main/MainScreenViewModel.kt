package com.leoevg.san_dinner.presentation.screen.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leoevg.san_dinner.data.manager.SharedPrefManager
import com.leoevg.san_dinner.data.repository.MenuRepository
import com.leoevg.san_dinner.presentation.alarm.AlarmScheduler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val sharedPrefManager: SharedPrefManager,
    private val menuRepository: MenuRepository,
    private val alarmScheduler: AlarmScheduler
) : ViewModel() {

    private val _state = MutableStateFlow(MainScreenState())
    val state: StateFlow<MainScreenState> = _state.asStateFlow()

    init {
        _state.update {
            it.copy(
                firstName = sharedPrefManager.firstName,
                workerID = sharedPrefManager.workerID,
                language = sharedPrefManager.language,
                notificationsEnabled = sharedPrefManager.notificationsEnabled
            )
        }
        loadMenu()
        
        // Устанавливаем будильник при запуске
        alarmScheduler.scheduleDailyAlarm()
    }

    private fun loadMenu() {
        viewModelScope.launch(Dispatchers.IO) {
            _state.update { it.copy(isLoading = true) }
            val menuItems = menuRepository.getMenu()
            val mainDishes = menuItems.filter { it.type.contains("main", ignoreCase = true) }
            val sideDishes = menuItems.filter { it.type.contains("side", ignoreCase = true) }

            _state.update {
                it.copy(
                    mainDishes = mainDishes,
                    sideDishes = sideDishes,
                    isLoading = false
                )
            }
        }
    }

    fun onEvent(event: MainScreenEvent) {
        when (event) {
            is MainScreenEvent.NameUpdated -> {
                sharedPrefManager.firstName = event.name
                _state.update { it.copy(firstName = event.name) }
            }
            is MainScreenEvent.WorkerIDUpdated -> {
                sharedPrefManager.workerID = event.workerID
                _state.update { it.copy(workerID = event.workerID) }
            }
            is MainScreenEvent.LanguageUpdated -> {
                sharedPrefManager.language = event.language
                _state.update { it.copy(language = event.language) }
            }
            is MainScreenEvent.NotificationsUpdated -> {
                sharedPrefManager.notificationsEnabled = event.enabled
                _state.update { it.copy(notificationsEnabled = event.enabled) }
            }
            is MainScreenEvent.MainDishSelected -> {
                _state.update { it.copy(selectedMainDishId = event.id) }
            }
            is MainScreenEvent.SideDishSelected -> {
                _state.update { it.copy(selectedSideDishId = event.id) }
            }
            is MainScreenEvent.OrderConfirmed -> {
                // Сохраняем дату заказа, чтобы будильник не сработал сегодня
                sharedPrefManager.lastOrderDate = LocalDate.now().toString()
            }
        }
    }
}
