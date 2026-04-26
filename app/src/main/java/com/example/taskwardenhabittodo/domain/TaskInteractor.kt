package com.example.taskwardenhabittodo.domain

import com.example.taskwardenhabittodo.data.TaskData
import com.example.taskwardenhabittodo.domain.use.cases.AddTasksUseCase
import com.example.taskwardenhabittodo.domain.use.cases.DeleteTasksUseCase
import com.example.taskwardenhabittodo.domain.use.cases.GetAllTasksUseCase
import com.example.taskwardenhabittodo.domain.use.cases.UpdateCompletionUseCase
import com.example.taskwardenhabittodo.domain.use.cases.UpdateHabitProgressUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TaskInteractor @Inject constructor(
    private val getAllTasksUseCase: GetAllTasksUseCase,
    private val addTasksUseCase: AddTasksUseCase,
    private val deleteTasksUseCase: DeleteTasksUseCase,
    private val updateCompletionUseCase: UpdateCompletionUseCase,
    private val updateHabitProgressUseCase: UpdateHabitProgressUseCase,
){
    fun getAllTasks(): Flow<List<TaskData>> {
        return getAllTasksUseCase.getAllTasks()
    }

    suspend fun addTasks(task: TaskData){
        addTasksUseCase.addTasks(task)
    }

    suspend fun deleteTasks(task: TaskData){
        deleteTasksUseCase.deleteTasks(task)
    }

    suspend fun updateCompletion (id: Int, isCompleted: Boolean){
        updateCompletionUseCase.updateCompletion(id, isCompleted)
    }

    suspend fun updateHabitProgress(id: Int, count: Int) {
        updateHabitProgressUseCase.updateHabitProgress(id, count)
    }
}