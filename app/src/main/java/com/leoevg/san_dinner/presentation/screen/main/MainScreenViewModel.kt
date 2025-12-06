package com.leoevg.san_dinner.presentation.screen.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leoevg.san_dinner.data.googleFormsApi.GoogleFormApi
import com.leoevg.san_dinner.data.manager.SharedPrefManager
import com.leoevg.san_dinner.data.repository.MenuRepository
import com.leoevg.san_dinner.presentation.alarm.AlarmScheduler
import com.leoevg.san_dinner.presentation.navigation.Screen
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.invoke
import kotlinx.coroutines.launch
import kotlinx.coroutines.selects.select
import okhttp3.Dispatcher
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.Locale
import javax.inject.Inject

@HiltViewModel(assistedFactory = MainScreenViewModel.Factory::class)
class MainScreenViewModel @AssistedInject constructor(
    private val sharedPrefManager: SharedPrefManager,
    private val menuRepository: MenuRepository,
    private val googleFormApi: GoogleFormApi,
    alarmScheduler: AlarmScheduler,
    @Assisted private val navigateTo: (Screen) -> Unit
) : ViewModel() {

    private val _state = MutableStateFlow(MainScreenState(
        firstName = sharedPrefManager.firstName,
        workerID = sharedPrefManager.workerID,
        language = sharedPrefManager.language,
        notificationsEnabled = sharedPrefManager.notificationsEnabled
    ))
    val state: StateFlow<MainScreenState> = _state.asStateFlow()

    fun onEvent(event: MainScreenEvent) {
        when (event) {
            is MainScreenEvent.NameUpdated -> onNameUpdated(event.name)
            is MainScreenEvent.WorkerIDUpdated -> onWorkerIDUpdated(event.workerID)
            is MainScreenEvent.LanguageUpdated -> onLanguageUpdated(event.language)
            is MainScreenEvent.NotificationsUpdated -> onNotificationsUpdated(event.enabled)
            is MainScreenEvent.MainDishSelected -> onMainDishSelected(event.id)
            is MainScreenEvent.SideDishSelected -> onSideDishSelected(event.id)
            is MainScreenEvent.OrderConfirmed -> onOrderConfirmed()
        }
    }

    private fun onNameUpdated(name: String) {
        sharedPrefManager.firstName = name
        _state.update { it.copy(firstName = name) }
    }

    private fun onWorkerIDUpdated(workerID: String) {
        sharedPrefManager.workerID = workerID
        _state.update { it.copy(workerID = workerID) }
    }

    private fun onLanguageUpdated(language: String) {
        sharedPrefManager.language = language
        _state.update { it.copy(language = language) }
    }

    private fun onNotificationsUpdated(enabled: Boolean) {
        sharedPrefManager.notificationsEnabled = enabled
        _state.update { it.copy(notificationsEnabled = enabled) }
    }

    private fun onMainDishSelected(id: String) {
        _state.update { it.copy(selectedMainDishId = id) }
    }

    private fun onSideDishSelected(id: String) {
        _state.update { it.copy(selectedSideDishId = id) }
    }

    private fun onOrderConfirmed() {
        // Сохраняем дату заказа, чтобы будильник не сработал сегодня
        sharedPrefManager.lastOrderDate = LocalDate.now().toString()

        submitForm()
    }

    private fun loadMenu() = viewModelScope.launch(Dispatchers.IO) {
        _state.update { it.copy(isLoading = true) }
        val menuItems = menuRepository.getMenu()

        val currentDay = LocalDate.now().dayOfWeek.getDisplayName(TextStyle.FULL, Locale.ENGLISH)
        val filteredItems = menuItems.filter { item ->
            item.days.any { it.equals(currentDay, ignoreCase = true) }
        }

        val mainDishes = filteredItems.filter { it.type.contains("main", ignoreCase = true) }
        val sideDishes = filteredItems.filter { it.type.contains("side", ignoreCase = true) }

        _state.update {
            it.copy(
                mainDishes = mainDishes,
                sideDishes = sideDishes,
                isLoading = false
            )
        }
    }

    private fun submitForm() = viewModelScope.launch {
        clearErrors()

        val mainDishId = state.value.selectedMainDishId!!
        val sideDishId = state.value.selectedSideDishId!!

        val (mainDishName, sideDishName) = getSelectedDishesNames(mainDishId, sideDishId)
        
        // Save selected dishes in current language for AfterScreen
        saveOrderForAfterScreen(mainDishId, sideDishId)

        val response = Dispatchers.IO { googleFormApi.submitForm(
            name = state.value.firstName,
            workerId = state.value.workerID,
            selectedDishesNames = "$mainDishName, $sideDishName"
        ) }
        if (response.isSuccessful) {
            navigateTo(Screen.After)
        } else {
            _state.update { it.copy(responseSentSuccessfully = false) }
        }
    }

    private fun clearErrors() {
        _state.update { it.copy(responseSentSuccessfully = null) }
    }

    private fun getSelectedDishesNames(mainDishId: String, sideDishId: String): Pair<String, String> {
        /**
         * Returns selected dishes names in Hebrew
         */

        val mainDish = state.value.mainDishes.find { it.id == mainDishId }?.lang["he"] ?: ""
        val sideDish = state.value.sideDishes.find { it.id == sideDishId }?.lang["he"] ?: ""

        return Pair(mainDish, sideDish)
    }
    
    private fun saveOrderForAfterScreen(mainDishId: String, sideDishId: String) {
        // Save using current selected language or fallback to 'he' or 'en' or id
        val currentLang = state.value.language.lowercase()
        
        val mainDish = state.value.mainDishes.find { it.id == mainDishId }?.let { dish ->
            dish.lang[currentLang] ?: dish.lang["en"] ?: dish.lang["he"] ?: dish.id
        } ?: ""
        
        val sideDish = state.value.sideDishes.find { it.id == sideDishId }?.let { dish ->
            dish.lang[currentLang] ?: dish.lang["en"] ?: dish.lang["he"] ?: dish.id
        } ?: ""
        
        sharedPrefManager.lastOrderMainDish = mainDish
        sharedPrefManager.lastOrderSideDish = sideDish
    }

    init {
        loadMenu()

        // Устанавливаем будильник при запуске
        alarmScheduler.scheduleDailyAlarm()
    }

    @AssistedFactory
    interface Factory {
        fun create(navigateTo: (Screen) -> Unit): MainScreenViewModel
    }
}
