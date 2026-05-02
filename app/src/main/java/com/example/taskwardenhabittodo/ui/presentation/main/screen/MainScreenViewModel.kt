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
    import dagger.hilt.android.lifecycle.HiltViewModel
    import kotlinx.coroutines.flow.SharingStarted
    import kotlinx.coroutines.flow.StateFlow
    import kotlinx.coroutines.flow.combine
    import kotlinx.coroutines.flow.stateIn
    import kotlinx.coroutines.launch
    import java.util.Calendar
    import javax.inject.Inject


    @HiltViewModel
    class MainScreenViewModel @Inject constructor(
        private val taskInteractor: TaskInteractor,
        private val habitInteractor: HabitInteractor,
        private val userInteractor: UserInteractor
    ): ViewModel(){

        private fun getStartOfDay(): Long{
            return Calendar.getInstance().apply {
                set(Calendar.HOUR_OF_DAY, 0)
                set(Calendar.MINUTE, 0)
                set(Calendar.SECOND, 0)
                set(Calendar.MILLISECOND, 0)
            }.timeInMillis
        }

        val uiState: StateFlow<MainScreenState> = combine(
            userInteractor.getUserStats(),
            taskInteractor.getTodayTaskStats(getStartOfDay()),
            habitInteractor.getTodayHabitStats(getStartOfDay()),
            habitInteractor.getAllHabits()
        ){user, taskStats, habitStats, allItems ->

            val dayProgress = DayProgress(
                totalTasks = taskStats.second,
                completedTasks = taskStats.first,
                totalHabits = habitStats.second,
                completedHabits = habitStats.first
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
                todayHabits = allItems.filter {
                    it.isHabit
                }.map {
                    it.toUi()
                },
                focusTask = allItems.firstOrNull(){
                    !it.isHabit && !it.isCompleted
                }?.toUi()
            )
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = MainScreenState()
        )

        fun addHabit(uiHabit: UiTaskData){
            viewModelScope.launch {
                habitInteractor.addHabit(uiHabit.toDomain())
            }
        }

        fun updateHabitProgress(habitId: Int, newCount: Int){
            viewModelScope.launch {
                habitInteractor.updateHabitProgress(habitId, newCount)
            }
        }

        fun toggleTaskCompletion(taskId: Int, isCompleted: Boolean){
            viewModelScope.launch {
                taskInteractor.updateCompletion(taskId, isCompleted)
            }
        }

        fun deleteHabitById(id: Int){
            viewModelScope.launch {
                habitInteractor.deleteHabitById(id)
            }
        }
    }