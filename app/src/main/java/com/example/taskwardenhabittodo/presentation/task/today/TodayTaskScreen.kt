package com.example.taskwardenhabittodo.presentation.task.today

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Velocity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.taskwardenhabittodo.R
import com.example.taskwardenhabittodo.domain.item.ActionType
import com.example.taskwardenhabittodo.domain.item.DayPart
import com.example.taskwardenhabittodo.domain.item.Priority
import com.example.taskwardenhabittodo.presentation.item.UiTaskData
import com.example.taskwardenhabittodo.ui.components.ActionIconButton
import com.example.taskwardenhabittodo.ui.components.FocusTaskCard
import com.example.taskwardenhabittodo.ui.components.HistoryArchiveCard
import com.example.taskwardenhabittodo.ui.components.bottom.sheet.BottomSheetScreen
import com.example.taskwardenhabittodo.ui.components.task.TaskCard
import com.example.taskwardenhabittodo.ui.components.task.TaskDismissibleContainer
import com.example.taskwardenhabittodo.ui.components.task.TaskSectionHeader
import com.example.taskwardenhabittodo.ui.theme.AmberGold
import com.example.taskwardenhabittodo.ui.theme.SuccessGreen
import com.example.taskwardenhabittodo.ui.theme.WarningRed
import com.example.taskwardenhabittodo.ui.theme.spacing
import kotlinx.coroutines.launch

@Composable
fun TasksScreen(
    viewModel: TodayTaskScreenViewModel = hiltViewModel(),
    onNavigateToArchive: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val spacing = MaterialTheme.spacing
    val colorScheme = MaterialTheme.colorScheme

    val maxOffset = 200f
    val snapThreshold = 60f

    val archiveAnim = remember { Animatable(0f) }
    val scope = rememberCoroutineScope()
    val scrollState = rememberLazyListState()

    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                val offset = archiveAnim.value

                if (available.y > 0 &&
                    scrollState.firstVisibleItemIndex == 0 &&
                    scrollState.firstVisibleItemScrollOffset == 0 &&
                    offset < maxOffset
                ) {
                    val newVal = (offset + available.y * 0.4f).coerceIn(0f, maxOffset)
                    scope.launch { archiveAnim.snapTo(newVal) }
                    return Offset(0f, available.y)
                }
                if (available.y < 0 &&
                    offset > 0f &&
                    scrollState.firstVisibleItemIndex == 0 &&
                    scrollState.firstVisibleItemScrollOffset == 0
                ) {
                    val newVal = (offset + available.y * 0.8f).coerceIn(0f, maxOffset)
                    scope.launch { archiveAnim.snapTo(newVal) }
                    return Offset(0f, available.y)
                }
                return Offset.Zero
            }

            override suspend fun onPostFling(consumed: Velocity, available: Velocity): Velocity {
                val offset = archiveAnim.value

                if (offset > 0f && offset < maxOffset) {
                    val target = if (offset > snapThreshold) maxOffset else 0f
                    archiveAnim.animateTo(
                        targetValue = target,
                        animationSpec = spring(
                            dampingRatio = Spring.DampingRatioMediumBouncy,
                            stiffness = Spring.StiffnessMedium
                        )
                    )
                    return Velocity(0f, available.y)
                }
                return super.onPostFling(consumed, available)
            }
        }
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(nestedScrollConnection),
        topBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = spacing.medium, vertical = spacing.small)
            ) {
                Text(
                    text = uiState.displayDate,
                    style = MaterialTheme.typography.labelMedium,
                    color = colorScheme.onSurfaceVariant
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(R.string.task_title_header),
                        style = MaterialTheme.typography.headlineLarge.copy(
                            fontWeight = FontWeight.Bold
                        )
                    )
                    ActionIconButton(
                        iconRes = R.drawable.add,
                        onClick = { viewModel.showBottomSheet() },
                        isSelected = true,
                        actionColor = colorScheme.primary,
                        containerSize = 48.dp
                    )
                }
            }
        },
        containerColor = colorScheme.background
    ) { paddingValues ->

        if (uiState.isLoading) {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                state = scrollState,
                contentPadding = PaddingValues(spacing.medium)
            ) {
                item {
                    FocusTaskCard(
                        modifier = Modifier.zIndex(2f),
                        title = stringResource(R.string.stats_daily_progress),
                        completedCount = uiState.progress.completedCount,
                        totalCount = uiState.progress.totalCount,
                        progress = uiState.progress.percentage,
                        isFullMode = false,
                        streak = uiState.taskStreak
                    )
                    Spacer(modifier = Modifier.height(spacing.medium))
                }

                item {
                    val progress = (archiveAnim.value / maxOffset).coerceIn(0f, 1f)
                    val currentCardHeight = (72 * progress).dp
                    val topSpacing = (spacing.medium * progress)
                    val bottomSpacing = (spacing.extraLarge - (spacing.medium * progress))

                    if (topSpacing > 0.dp) {
                        Spacer(modifier = Modifier.height(topSpacing))
                    }

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(currentCardHeight)
                            .zIndex(1f)
                    ) {
                        HistoryArchiveCard(
                            progress = progress,
                            modifier = Modifier.clickable { onNavigateToArchive() }
                        )
                    }

                    Spacer(modifier = Modifier.height(bottomSpacing))
                }

                uiState.sections.forEach { section ->
                    item {
                        TaskSectionHeader(
                            title = section.title,
                            iconRes = section.iconRes,
                            doneCount = section.tasks.count { it.isCompleted },
                            totalCount = section.tasks.size
                        )
                        Spacer(modifier = Modifier.height(spacing.small))
                    }

                    items(
                        items = section.tasks,
                        key = { it.id }
                    ) { task ->
                        Box(modifier = Modifier.animateItem()) {
                            TaskDismissibleContainer(
                                onRemove = { viewModel.deleteTaskById(task.id) }
                            ) {
                                TaskCard(
                                    time = task.time,
                                    period = task.period,
                                    title = task.title,
                                    duration = 15,
                                    priorityColor = when (task.priority) {
                                        Priority.HIGH -> WarningRed
                                        Priority.MEDIUM -> AmberGold
                                        Priority.LOW -> SuccessGreen
                                        Priority.NONE -> null
                                    },
                                    isCompleted = task.isCompleted,
                                    onClick = { viewModel.toggleTaskCompletion(task) }
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(spacing.small))
                    }

                    item {
                        Spacer(modifier = Modifier.height(spacing.medium))
                    }
                }
            }
        }

        if (uiState.isBottomSheetVisible) {
            BottomSheetScreen(
                type = ActionType.TASK,
                onDismiss = { viewModel.hideBottomSheet() },
                onCreateTask = { input ->
                    val newTask = UiTaskData(
                        title = input.title,
                        priority = input.priority,
                        time = input.time ?: "",
                        description = "",
                        period = "",
                        dayPart = DayPart.ALL_DAY,
                        isPinned = false
                    )
                    viewModel.addTask(newTask)
                }
            )
        }
    }
}
