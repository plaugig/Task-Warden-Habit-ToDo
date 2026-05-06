package com.example.taskwardenhabittodo.presentation.habit


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskwardenhabittodo.domain.interactor.HabitInteractor
import com.example.taskwardenhabittodo.domain.interactor.TaskInteractor
import com.example.taskwardenhabittodo.domain.interactor.UserInteractor
import com.example.taskwardenhabittodo.presentation.habit.item.DateUtils
import com.example.taskwardenhabittodo.ui.DayProgress
import com.example.taskwardenhabittodo.ui.UiTaskData
import com.example.taskwardenhabittodo.ui.UiUserData
import com.example.taskwardenhabittodo.ui.maper.toDomain
import com.example.taskwardenhabittodo.ui.maper.toUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class habitScreenViewModel @Inject constructor(
    private val taskInteractor: TaskInteractor,
    private val habitInteractor: HabitInteractor,
    private val userInteractor: UserInteractor
) : ViewModel() {

    private val startOfDAyFlow = MutableStateFlow(DateUtils.getStartOfDay())

    val uiState: StateFlow<HabitScreenState> = startOfDAyFlow.flatMapLatest { startDay ->
        combine(
            userInteractor.getUserStats(),
            taskInteractor.getTodayTaskStats(startDay),
            habitInteractor.getTodayHabitStats(startDay),
            habitInteractor.getAllHabits(),
            taskInteractor.getAllTasks()
        ) { user, taskStats, habitStats, allHabit, allTask ->

            val dayProgress = DayProgress(
                totalTasks = taskStats.second,
                completedTasks = taskStats.first,
                totalHabits = habitStats.first,
                completedHabits = habitStats.second
            )

            HabitScreenState(
                isLoading = false,
                user = user?.toUi() ?: UiUserData(
                    petPoints = 0,
                    dailyPoints = 0,
                    fireStreak = 0,
                    masteryStreak = 0
                ),
                progress = dayProgress,
                todayHabits = allHabit
                    .filter { it.classification }
                    .map { it.toUi() }
                    .sortedBy { it.isCompleted },

                focusTask = allTask.firstOrNull() {
                    !it.classification && !it.isCompleted
                }?.toUi()
            )
        }
    }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = HabitScreenState()
        )

    fun addHabit(uiHabit: UiTaskData) {
        viewModelScope.launch {
            habitInteractor.addHabit(uiHabit.toDomain())
        }
    }

    fun updateHabitProgress(habitId: Int, currentCount: Int) {
        viewModelScope.launch {
            val habit = uiState.value.todayHabits.find {
                it.id == habitId
            }
            habit?.let {
                if (currentCount >= it.targetCount) return@launch
            }

            val newCount = currentCount + 1
            habitInteractor.updateHabitProgress(habitId, newCount)
        }
    }

    fun deleteHabitById(id: Int) {
        viewModelScope.launch {
            habitInteractor.deleteHabitById(id)
        }
    }
}

