package com.example.taskwardenhabittodo.presentation.cat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskwardenhabittodo.domain.interactor.GameInteractor
import com.example.taskwardenhabittodo.domain.pet.enums.CatActionType
import com.example.taskwardenhabittodo.presentation.cat.item.CatUiState
import com.example.taskwardenhabittodo.presentation.cat.item.toCatUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CatViewModel @Inject constructor(
    private val gameInteractor: GameInteractor
): ViewModel() {

    init {
        viewModelScope.launch {
            gameInteractor.applyCatDecay()
        }
    }

    val uiState: StateFlow<CatUiState> = gameInteractor.observeGame()
        .map { game -> game.toCatUiState() }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = CatUiState()
        )

    fun onAction(action: CatActionType){
        viewModelScope.launch {
            gameInteractor.performCatAction(action)
        }
    }
}