package com.leoevg.san_dinner.presentation.screen.main

import androidx.lifecycle.ViewModel
import com.leoevg.san_dinner.data.local.PreferencesManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val preferencesManager: PreferencesManager
) : ViewModel() {

    private val _state = MutableStateFlow(MainScreenState())
    val state: StateFlow<MainScreenState> = _state.asStateFlow()

    init {
        _state.update {
            it.copy(
                firstName = preferencesManager.firstName,
                workerID = preferencesManager.workerID,
                language = preferencesManager.language,
                notificationsEnabled = preferencesManager.notificationsEnabled
            )
        }
    }

    fun onEvent(event: MainScreenEvent) {
        when (event) {
            is MainScreenEvent.NameUpdated -> {
                preferencesManager.firstName = event.name
                _state.update { it.copy(firstName = event.name) }
            }
            is MainScreenEvent.WorkerIDUpdated -> {
                preferencesManager.workerID = event.workerID
                _state.update { it.copy(workerID = event.workerID) }
            }
            is MainScreenEvent.LanguageUpdated -> {
                preferencesManager.language = event.language
                _state.update { it.copy(language = event.language) }
            }
            is MainScreenEvent.NotificationsUpdated -> {
                preferencesManager.notificationsEnabled = event.enabled
                _state.update { it.copy(notificationsEnabled = event.enabled) }
            }
        }
    }
}