package com.example.taskwardenhabittodo.domain.use.cases

import com.example.taskwardenhabittodo.domain.repository.TaskRepository
import javax.inject.Inject

class UpdateCompletionUseCase @Inject constructor(
    private val repository : TaskRepository
) {

    suspend fun updateCompletion (id: Int, isCompleted: Boolean){
        repository.updateCompletion(id, isCompleted)
    }
}