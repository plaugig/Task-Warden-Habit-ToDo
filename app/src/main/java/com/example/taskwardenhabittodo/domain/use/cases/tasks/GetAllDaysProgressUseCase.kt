package com.example.taskwardenhabittodo.domain.use.cases.tasks

import com.example.taskwardenhabittodo.domain.repository.TaskRepository
import com.example.taskwardenhabittodo.presentation.archive.item.DayProgressUiData
import com.example.taskwardenhabittodo.presentation.archive.item.TaskDateUtils
import com.example.taskwardenhabittodo.ui.maper.toUi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetAllDaysProgressUseCase @Inject constructor(
    private val repository: TaskRepository
) {
    fun getAllDaysProgress(): Flow<List<DayProgressUiData>> {
        val startOfDay = TaskDateUtils.getStartOfToday()

        return repository.getAllDaysProgress().map { allDays ->
            allDays
                .filter { it.dateTimestamp < startOfDay }
                .map { it.toUi() }
        }
    }
}