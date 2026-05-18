package com.example.taskwardenhabittodo.presentation.archive

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskwardenhabittodo.domain.interactor.TaskInteractor
import com.example.taskwardenhabittodo.presentation.archive.item.ArchiveUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class ArchiveDaysViewModel @Inject constructor(
    private val taskInteractor: TaskInteractor
) : ViewModel() {
    val uiState: StateFlow<ArchiveUiState> = taskInteractor.getAllDaysProgress()
        .map { days ->
            if (days.isEmpty()) ArchiveUiState.Empty
            else ArchiveUiState.Success(days)
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = ArchiveUiState.Loading
        )
}