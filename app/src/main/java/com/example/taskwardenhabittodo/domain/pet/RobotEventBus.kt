package com.example.taskwardenhabittodo.domain.pet

import com.example.taskwardenhabittodo.domain.pet.enums.RobotContext
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class RobotEventBus @Inject constructor() {

    private val _events = MutableSharedFlow<RobotContext>(extraBufferCapacity = 4)
    val events : SharedFlow<RobotContext> = _events.asSharedFlow()

    fun emit (context: RobotContext){
        _events.tryEmit(context)
    }
}