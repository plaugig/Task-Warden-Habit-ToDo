package com.example.taskwardenhabittodo.ui.presentation.main.screen


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskwardenhabittodo.domain.interactor.HabitInteractor
import com.example.taskwardenhabittodo.domain.interactor.TaskInteractor
import com.example.taskwardenhabittodo.domain.interactor.UserInteractor
import com.example.taskwardenhabittodo.ui.DayProgress
import com.example.taskwardenhabittodo.ui.UiTaskData
import com.example.taskwardenhabittodo.ui.UiUserData
import com.example.taskwardenhabittodo.ui.maper.toDomain
import com.example.taskwardenhabittodo.ui.maper.toUi
import com.example.taskwardenhabittodo.ui.presentation.main.screen.item.DateUtils
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
class MainScreenViewModel @Inject constructor(
    private val taskInteractor: TaskInteractor,
    private val habitInteractor: HabitInteractor,
    private val userInteractor: UserInteractor
) : ViewModel() {

    private val startOfDAyFlow = MutableStateFlow(DateUtils.getStartOfDay())

    val uiState: StateFlow<MainScreenState> = startOfDAyFlow.flatMapLatest { startDay ->
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

            MainScreenState(
                isLoading = false,
                user = user?.toUi() ?: UiUserData(
                    petPoints = 0,
                    dailyPoints = 0,
                    fireStreak = 0,
                    masteryStreak = 0
                ),
                progress = dayProgress,
                todayHabits = allHabit
                    .filter { it.isHabit }
                    .map { it.toUi() }
                    .sortedBy { it.isCompleted },

                focusTask = allTask.firstOrNull() {
                    !it.isHabit && !it.isCompleted
                }?.toUi()
            )
        }
    }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = MainScreenState()
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

