package com.example.taskwardenhabittodo.presentation.tasks.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskwardenhabittodo.domain.interactor.TaskInteractor
import com.example.taskwardenhabittodo.presentation.tasks.history.item.HistoryDayTaskDateUtils
import com.example.taskwardenhabittodo.presentation.tasks.history.item.HistoryDayTaskScreenState
import com.example.taskwardenhabittodo.presentation.item.toUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class HistoryDayTaskViewModel @Inject constructor(
    private val taskInteractor: TaskInteractor,
) : ViewModel() {

    private val archiveDayTimestampFlow = MutableStateFlow<Long?>(null)

    @OptIn(ExperimentalCoroutinesApi::class)
    val uiState: StateFlow<HistoryDayTaskScreenState> = archiveDayTimestampFlow.flatMapLatest { startDay ->
        if (startDay == null) {
            kotlinx.coroutines.flow.flowOf(HistoryDayTaskScreenState(isLoading = true))
        } else {
            val endDay = startDay + 86399999L

            taskInteractor.getTasksForDay(startDay, endDay).map { tasks ->
                val uiTasks = tasks.map { it.toUi() }

                HistoryDayTaskScreenState(
                    isLoading = false,
                    displayDate = HistoryDayTaskDateUtils.formatArchiveDisplayDate(startDay),
                    progress = HistoryDayTaskDateUtils.calculateProgress(uiTasks),
                    sections = HistoryDayTaskDateUtils.prepareArchiveSections(uiTasks)
                )
            }
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = HistoryDayTaskScreenState(isLoading = true)
    )

    fun setArchiveDay(timestamp: Long) {
        if (archiveDayTimestampFlow.value == null) {
            archiveDayTimestampFlow.value = timestamp
        }
    }
}