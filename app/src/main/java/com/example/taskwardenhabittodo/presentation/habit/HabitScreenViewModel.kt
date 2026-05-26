package com.example.taskwardenhabittodo.presentation.habit


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskwardenhabittodo.domain.interactor.HabitInteractor
import com.example.taskwardenhabittodo.domain.interactor.TaskInteractor
import com.example.taskwardenhabittodo.domain.interactor.UserInteractor
import com.example.taskwardenhabittodo.presentation.habit.item.HabitDateUtils
import com.example.taskwardenhabittodo.presentation.habit.item.HabitScreenState
import com.example.taskwardenhabittodo.presentation.item.DayProgress
import com.example.taskwardenhabittodo.presentation.item.UiHabitData
import com.example.taskwardenhabittodo.presentation.item.UiUserData
import com.example.taskwardenhabittodo.presentation.item.toDomain
import com.example.taskwardenhabittodo.presentation.item.toUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HabitScreenViewModel @Inject constructor(
    private val taskInteractor: TaskInteractor,
    private val habitInteractor: HabitInteractor,
    private val userInteractor: UserInteractor
) : ViewModel() {

    init {
        calculateTaskStreak()
        calculateHabitStreak()
    }

    private val startOfDayFlow = MutableStateFlow(HabitDateUtils.getStartOfDay())

    val uiState: StateFlow<HabitScreenState> = startOfDayFlow.flatMapLatest { startDay ->
        val endDay = startDay + 86399999L

        combine(
            userInteractor.getUserStats(),
            taskInteractor.getTaskStats(startDay),
            habitInteractor.getTodayHabitStats(),
            habitInteractor.getAllHabits(),
            taskInteractor.getTasksForDay(startDay, endDay)
        ) { user, taskStats, habitStats, allHabits, todayTasks ->

            val dayProgress = DayProgress(
                totalTasks = taskStats.totalCount,
                completedTasks = taskStats.completedCount,
                totalHabits = habitStats.totalCount,
                completedHabits = habitStats.completedCount
            )

            HabitScreenState(
                isLoading = false,
                user = user?.toUi() ?: UiUserData(
                    petPoints = 0,
                    dailyPoints = 0,
                    taskStreak = 0,
                    habitStreak = 0
                ),
                progress = dayProgress,
                todayHabits = allHabits
                    .map { it.toUi() }
                    .sortedBy { it.isCompleted },
                focusTask = todayTasks
                    .firstOrNull { !it.isCompleted }
                    ?.toUi()
            )
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = HabitScreenState()
    )

    fun addHabit(uiHabit: UiHabitData) {
        viewModelScope.launch(Dispatchers.IO) {
            habitInteractor.addHabit(uiHabit.toDomain())
        }
    }

    fun updateHabitProgress(habitId: Int, currentCount: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val habit = uiState.value.todayHabits.find { it.id == habitId }
            habit?.let {
                if (currentCount >= it.targetCount) return@launch
            }
            val newCount = currentCount + 1
            habitInteractor.updateHabitProgress(habitId, newCount)
        }
    }

    fun deleteHabitById(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            habitInteractor.deleteHabitById(id)
        }
    }

    private fun calculateTaskStreak() {
        viewModelScope.launch(Dispatchers.IO) {
            taskInteractor.calculateTaskStreak()
        }
    }

    private fun calculateHabitStreak(){
        viewModelScope.launch(Dispatchers.IO){
            habitInteractor.calculateHabitStreak()
        }
    }

}

