package com.example.taskwardenhabittodo.domain.use.cases.tasks

import com.example.taskwardenhabittodo.data.repository.impl.TaskRepositoryImpl
import javax.inject.Inject

class UpdateCompletionUseCase @Inject constructor(
    private val repository : TaskRepositoryImpl
){
    suspend fun updateCompletion(id: Int, isCompleted: Boolean) {
        repository.updateCompletion(id, isCompleted)
    }
}