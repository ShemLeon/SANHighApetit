package com.leoevg.san_dinner.presentation.screen.main

sealed class MainScreenEvent {
    data class NameUpdated(val name: String) : MainScreenEvent()
    data class WorkerIDUpdated(val workerID: String) : MainScreenEvent()
}