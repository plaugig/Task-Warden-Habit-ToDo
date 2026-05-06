package com.example.taskwardenhabittodo.domain

import com.example.taskwardenhabittodo.R

enum class Priority {
    HIGH, MEDIUM, LOW, NONE
}

enum class CategoryType (val iconResId: Int) {
    WORK(R.drawable.pen),
    PERSONAL(R.drawable.meditate),
    HEALTH(R.drawable.health),
    STUDY(R.drawable.read),
    FINANCE(R.drawable.coins),
    GENERAL(R.drawable.animal),
    SPORT(R.drawable.workout)
}
enum class DayPart {
    MORNING, AFTERNOON, EVENING, ALL_DAY
}

enum class ActionType {
    HABIT, TASK
}