package com.example.taskwardenhabittodo.core.application.time

fun interface Clock {
    fun now(): Long
}