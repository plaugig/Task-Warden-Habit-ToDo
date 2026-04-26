package com.example.taskwardenhabittodo.domain.use.cases

import com.example.taskwardenhabittodo.domain.repository.TaskRepository
import javax.inject.Inject

class ResetDailyPointsUserCase @Inject constructor(
    private val repository: TaskRepository
) {
    suspend fun resetDailyPoints(){
        repository.resetDailyPoints()
    }
}