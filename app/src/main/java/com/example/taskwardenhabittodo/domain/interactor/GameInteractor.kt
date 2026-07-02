package com.example.taskwardenhabittodo.domain.interactor

import com.example.taskwardenhabittodo.domain.item.data.GameData
import com.example.taskwardenhabittodo.domain.pet.GameConfig
import com.example.taskwardenhabittodo.domain.pet.RobotEventBus
import com.example.taskwardenhabittodo.domain.pet.enums.CatActionType
import com.example.taskwardenhabittodo.domain.pet.enums.RobotContext
import com.example.taskwardenhabittodo.domain.use.cases.games.ApplyCatDecayUseCase
import com.example.taskwardenhabittodo.domain.use.cases.games.AwardPointsForHabitUseCase
import com.example.taskwardenhabittodo.domain.use.cases.games.AwardPointsForTaskUseCase
import com.example.taskwardenhabittodo.domain.use.cases.games.CatActionResult
import com.example.taskwardenhabittodo.domain.use.cases.games.GetGameUseCase
import com.example.taskwardenhabittodo.domain.use.cases.games.GetRobotLineUseCase
import com.example.taskwardenhabittodo.domain.use.cases.games.PerformCatActionUseCase
import com.example.taskwardenhabittodo.domain.use.cases.games.RegisterFailureUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GameInteractor @Inject constructor(
    private val getGameUseCase: GetGameUseCase,
    private val applyCatDecayUseCase: ApplyCatDecayUseCase,
    private val performCatActionUseCase: PerformCatActionUseCase,
    private val awardPointsForTaskUseCase: AwardPointsForTaskUseCase,
    private val awardPointsForHabitUseCase: AwardPointsForHabitUseCase,
    private val registerFailureUseCase: RegisterFailureUseCase,
    private val getRobotLineUseCase: GetRobotLineUseCase,
    private val robotEventBus: RobotEventBus
) {
    fun observeGame(): Flow<GameData> = getGameUseCase()

    val robotEvents : Flow<RobotContext> get() = robotEventBus.events

    suspend fun applyCatDecay() = applyCatDecayUseCase()

    suspend fun performCatAction(action: CatActionType): CatActionResult {
        val result = performCatActionUseCase(action)

        if (result is CatActionResult.Success) {

            val context = when (action) {
                CatActionType.FEED,
                CatActionType.WATER,
                CatActionType.TREAT -> RobotContext.PET_FED

                CatActionType.PET,
                CatActionType.PLAY,
                CatActionType.CLEAN -> RobotContext.PET_PETTED
            }
            robotEventBus.emit(context)

            if (result.remainingPoints < GameConfig.LOW_POINTS_THRESHOLD) {
                robotEventBus.emit(RobotContext.LOW_POINTS)
            }
        }

        return result
    }

    suspend fun awardForTask(isCompleted: Boolean) {
        awardPointsForTaskUseCase(isCompleted)
        if( isCompleted) robotEventBus.emit(RobotContext.TASK_DONE)
    }

    suspend fun awardForHabit(justCompleted: Boolean) {
        awardPointsForHabitUseCase(justCompleted)
        if (justCompleted) robotEventBus.emit(RobotContext.HABIT_DONE)
    }

    suspend fun registerFailure(misses: Int) {
        registerFailureUseCase(misses)
        if (misses > 0) robotEventBus.emit(RobotContext.FAILURE)
    }

    suspend fun robotLine(context: RobotContext, allowProfanity: Boolean = false): String =
        getRobotLineUseCase(context, allowProfanity)
}