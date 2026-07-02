package com.example.taskwardenhabittodo.presentation.robot

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskwardenhabittodo.domain.interactor.GameInteractor
import com.example.taskwardenhabittodo.domain.pet.enums.RobotContext
import com.example.taskwardenhabittodo.domain.pet.enums.RobotMood
import com.example.taskwardenhabittodo.presentation.robot.item.RobotOverlayState
import com.example.taskwardenhabittodo.presentation.robot.item.toRobotImageRes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class RobotOverlayViewModel @Inject constructor(
    private val gameInteractor: GameInteractor
) : ViewModel() {

    private val _state = MutableStateFlow(RobotOverlayState())
    val state: StateFlow<RobotOverlayState> = _state.asStateFlow()

    private var currentMood: RobotMood = RobotMood.NEUTRAL

    init {
        viewModelScope.launch {
            gameInteractor.observeGame().collect { game ->
                currentMood = game.robotMood
            }
        }

        viewModelScope.launch {
            gameInteractor.robotEvents.collect {context -> showLine(context) }
        }

        viewModelScope.launch {
            delay(GREETING_DELAY_MS)
            showLine(RobotContext.GREETING)
        }

        viewModelScope.launch {
            while (true) {
                delay(IDLE_INTERVAL_MS)
                if (!_state.value.visible) showLine(RobotContext.IDLE_NUDGE)
            }
        }
    }

    private fun showLine(context: RobotContext) {
        viewModelScope.launch {
            val line = gameInteractor.robotLine(context, allowProfanity = true)
            _state.value = RobotOverlayState(
                visible = true,
                fullText = line,
                robotRes = currentMood.toRobotImageRes()
            )
        }
    }

    fun dismiss() {
        _state.value = _state.value.copy(visible = false)
    }

    companion object {
        private const val GREETING_DELAY_MS = 1_500L
        private const val IDLE_INTERVAL_MS = 45_000L
    }
}