package com.example.taskwardenhabittodo.presentation.archive.item

sealed interface ArchiveUiState {
    object Loading : ArchiveUiState
    object Empty : ArchiveUiState
    data class Success(val days: List<DayProgressUiData>) : ArchiveUiState
}