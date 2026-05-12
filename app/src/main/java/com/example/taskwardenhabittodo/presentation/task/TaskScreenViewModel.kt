package com.example.taskwardenhabittodo.presentation.task

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskwardenhabittodo.R
import com.example.taskwardenhabittodo.domain.interactor.TaskInteractor
import com.example.taskwardenhabittodo.domain.interactor.UserInteractor
import com.example.taskwardenhabittodo.domain.item.DayPart
import com.example.taskwardenhabittodo.presentation.habit.item.HabitDateUtils
import com.example.taskwardenhabittodo.presentation.task.item.TaskDateUtils
import com.example.taskwardenhabittodo.presentation.task.item.TaskProgress
import com.example.taskwardenhabittodo.presentation.task.item.TaskScreenState
import com.example.taskwardenhabittodo.presentation.task.item.TaskSection
import com.example.taskwardenhabittodo.ui.UiTaskData
import com.example.taskwardenhabittodo.ui.maper.toDomain
import com.example.taskwardenhabittodo.ui.maper.toUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class TaskScreenViewModel @Inject constructor(
    private val taskInteractor: TaskInteractor,
    private val userInteractor: UserInteractor
) : ViewModel() {

    private val startOfDayFlow = MutableStateFlow(HabitDateUtils.getStartOfDay())

    private val _isBottomSheetVisible = MutableStateFlow(false)

    @OptIn(ExperimentalCoroutinesApi::class)
    val uiState: StateFlow<TaskScreenState> = startOfDayFlow.flatMapLatest { startDay ->
        combine(
            taskInteractor.getAllTasks(),
            taskInteractor.getTaskStats(startDay),
            userInteractor.getUserStats(),
            _isBottomSheetVisible
        ) { allTask, taskStats, userStats, isVisible ->

            val todayTasks = allTask
                .filter { !it.classification }
                .map { it.toUi() }

            TaskScreenState(
                isLoading = false,
                displayDate = TaskDateUtils.formatDisplayDate(startDay),
                fireStreak = userStats?.fireStreak ?: 0,
                progress = TaskProgress(
                    completedCount = taskStats.completedCount,
                    totalCount = taskStats.totalCount,
                    percentage = if (taskStats.totalCount > 0)
                        taskStats.completedCount.toFloat() / taskStats.totalCount else 0f
                ),
                sections = prepareSections(todayTasks),
                isBottomSheetVisible = isVisible

            )
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = TaskScreenState()
    )

    private fun prepareSections(tasks: List<UiTaskData>): List<TaskSection> {
        return listOf(
            TaskSection(
                title = "Morning",
                iconRes = R.drawable.ev_sun,
                tasks = tasks.filter { it.dayPart == DayPart.MORNING }
            ),
            TaskSection(
                title = "Afternoon",
                iconRes = R.drawable.sun,
                tasks = tasks.filter { it.dayPart == DayPart.AFTERNOON }
            ),
            TaskSection(
                title = "Evening",
                iconRes = R.drawable.moon,
                tasks = tasks.filter { it.dayPart == DayPart.EVENING }
            ),
            TaskSection(
                title = "Other",
                iconRes = R.drawable.animal,
                tasks = tasks.filter { it.dayPart == DayPart.ALL_DAY }
            )
        ).filter { it.tasks.isNotEmpty() }
    }

    private fun calculateProgress(tasks: List<UiTaskData>): TaskProgress {
        val completed = tasks.count { it.isCompleted }
        val total = tasks.size

        return TaskProgress(
            completedCount = completed,
            totalCount = total,
            percentage = if (total > 0) completed.toFloat() / total else 0f
        )
    }

    fun toggleTaskCompletion(task: UiTaskData) {
        viewModelScope.launch {
            taskInteractor.updateCompletion(task.id, !task.isCompleted)
        }
    }

    fun addTask(uiTask: UiTaskData) {
        viewModelScope.launch {

            val rawTime = uiTask.time

            val correctedTask = if (!rawTime.isNullOrBlank()) {
                val hourInt = rawTime.split(":").firstOrNull()?.toIntOrNull() ?: 12

                uiTask.copy(
                    period = if (hourInt < 12) "AM" else "PM",
                    dayPart = TaskDateUtils.determineDayPart(rawTime)
                )
            } else {
                uiTask.copy(
                    time = "",
                    period = "",
                    dayPart = DayPart.ALL_DAY
                )
            }
            taskInteractor.addTask(correctedTask.toDomain())
            hideBottomSheet()
        }
    }

     fun deleteTaskById (id: Int){
        viewModelScope.launch {
            taskInteractor.deleteTaskById(id)
        }
    }

    fun showBottomSheet() {
        _isBottomSheetVisible.value = true
    }

    fun hideBottomSheet() {
        _isBottomSheetVisible.value = false
    }
}