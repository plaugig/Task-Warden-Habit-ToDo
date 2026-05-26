package com.example.taskwardenhabittodo.presentation.tasks.history

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.taskwardenhabittodo.R
import com.example.taskwardenhabittodo.domain.item.Priority
import com.example.taskwardenhabittodo.ui.components.FocusTaskCard
import com.example.taskwardenhabittodo.ui.components.task.TaskCard
import com.example.taskwardenhabittodo.ui.components.task.TaskSectionHeader
import com.example.taskwardenhabittodo.ui.theme.AmberGold
import com.example.taskwardenhabittodo.ui.theme.SuccessGreen
import com.example.taskwardenhabittodo.ui.theme.WarningRed
import com.example.taskwardenhabittodo.ui.theme.spacing

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistoryDayTaskScreen(
    timestamp: Long,
    onBackClick: () -> Unit,
    viewModel: HistoryDayTaskViewModel = hiltViewModel()
) {
    LaunchedEffect(timestamp) {
        viewModel.setArchiveDay(timestamp)
    }

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val spacing = MaterialTheme.spacing
    val colorScheme = MaterialTheme.colorScheme

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text(
                            text = uiState.displayDate,
                            style = MaterialTheme.typography.labelMedium,
                            color = colorScheme.onSurfaceVariant
                        )
                        Text(
                            text = stringResource(R.string.task_title_header),
                            style = MaterialTheme.typography.headlineMedium.copy(
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            painter = painterResource(R.drawable.arrow_back),
                            contentDescription = "Назад"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = colorScheme.background
                )
            )
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
                contentPadding = PaddingValues(spacing.medium)
            ) {
                item {
                    FocusTaskCard(
                        title = stringResource(R.string.stats_daily_progress),
                        completedCount = uiState.progress.completedCount,
                        totalCount = uiState.progress.totalCount,
                        progress = uiState.progress.percentage,
                        isFullMode = false,
                        streak = 0
                    )
                    Spacer(modifier = Modifier.height(spacing.medium))
                }

                uiState.sections.forEach { section ->
                    item {
                        TaskSectionHeader(
                            title = section.titleRes,
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
                        TaskCard(
                            time = task.time,
                            period = task.period,
                            title = task.title,
                            priorityColor = when (task.priority) {
                                Priority.HIGH -> WarningRed
                                Priority.MEDIUM -> AmberGold
                                Priority.LOW -> SuccessGreen
                                Priority.NONE -> null
                            },
                            isCompleted = task.isCompleted,
                            onClick = {}
                        )
                        Spacer(modifier = Modifier.height(spacing.small))
                    }

                    item {
                        Spacer(modifier = Modifier.height(spacing.medium))
                    }
                }
            }
        }
    }
}