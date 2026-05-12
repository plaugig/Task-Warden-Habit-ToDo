package com.example.taskwardenhabittodo.domain.interactor

import com.example.taskwardenhabittodo.data.TaskData
import com.example.taskwardenhabittodo.domain.item.ProgressStats
import com.example.taskwardenhabittodo.domain.use.cases.tasks.AddTaskUseCase
import com.example.taskwardenhabittodo.domain.use.cases.tasks.DeleteTaskByIdUseCase
import com.example.taskwardenhabittodo.domain.use.cases.tasks.GetAllTasksUseCase
import com.example.taskwardenhabittodo.domain.use.cases.tasks.GetTasksByDayPartUseCase
import com.example.taskwardenhabittodo.domain.use.cases.tasks.GetTaskStatsUseCase
import com.example.taskwardenhabittodo.domain.use.cases.tasks.GetUnfinishedTasksUseCase
import com.example.taskwardenhabittodo.domain.use.cases.tasks.UpdateCompletionUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TaskInteractor @Inject constructor(
    private val addTaskUseCase: AddTaskUseCase,
    private val deleteTaskByIdUseCase: DeleteTaskByIdUseCase,
    private val getAllTasksUseCase: GetAllTasksUseCase,
    private val getTaskStatsUseCase: GetTaskStatsUseCase,
    private val updateCompletionUseCase: UpdateCompletionUseCase,
    private val getUnfinishedTasksUseCase: GetUnfinishedTasksUseCase,
    private val getTasksByDayPartUseCase: GetTasksByDayPartUseCase
) {

    suspend fun addTask(task: TaskData) {
        addTaskUseCase.addTask(task)
    }

    suspend fun deleteTaskById(id: Int) {
        deleteTaskByIdUseCase.deleteTaskById(id)
    }

    fun getAllTasks(): Flow<List<TaskData>> {
        return getAllTasksUseCase.getAllTasks()
    }

    fun getTaskStats(startOfDay: Long): Flow<ProgressStats> {
        return getTaskStatsUseCase.getTaskStats(startOfDay)
    }

    suspend fun updateCompletion(id: Int, isCompleted: Boolean) {
        updateCompletionUseCase.updateCompletion(id, isCompleted)
    }

    fun getUnfinishedTasks(
        startOfDay: Long,
        endOfDay: Long
    ): Flow<Int> {
        return getUnfinishedTasksUseCase.getUnfinishedTasks(startOfDay, endOfDay)
    }

    fun getTasksByDayPart(
        dayPart: String,
        startOfDay: Long
    ): Flow<List<TaskData>> {
        return getTasksByDayPartUseCase.getTasksByDayPart(dayPart, startOfDay)
    }

}

