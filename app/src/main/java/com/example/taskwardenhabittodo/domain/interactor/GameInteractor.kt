package com.example.taskwardenhabittodo.domain.interactor

import com.example.taskwardenhabittodo.domain.item.data.GameData
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
    private val getRobotLineUseCase: GetRobotLineUseCase
) {
    fun observeGame(): Flow<GameData> = getGameUseCase()

    suspend fun applyCatDecay() = applyCatDecayUseCase()

    suspend fun performCatAction(action: CatActionType): CatActionResult =
        performCatActionUseCase(action)

    suspend fun awardForTask(isCompleted: Boolean) = awardPointsForTaskUseCase(isCompleted)

    suspend fun awardForHabit(justCompleted: Boolean) = awardPointsForHabitUseCase(justCompleted)

    suspend fun registerFailure(misses: Int) = registerFailureUseCase(misses)

    suspend fun robotLine(context: RobotContext, allowProfanity: Boolean = false): String =
        getRobotLineUseCase(context, allowProfanity)
}