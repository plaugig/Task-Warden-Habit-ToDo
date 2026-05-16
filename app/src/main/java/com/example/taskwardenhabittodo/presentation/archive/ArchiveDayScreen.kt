package com.example.taskwardenhabittodo.presentation.archive
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.taskwardenhabittodo.R
import com.example.taskwardenhabittodo.presentation.archive.item.ArchiveUiState
import com.example.taskwardenhabittodo.presentation.archive.item.DayProgressUiData
import com.example.taskwardenhabittodo.presentation.archive.item.TaskDateUtils
import com.example.taskwardenhabittodo.ui.components.FocusTaskCard
import com.example.taskwardenhabittodo.ui.theme.spacing

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArchiveDayScreen(
    onBackClick: () -> Unit,
    onDayClick: (Long) -> Unit,
    viewModel: ArchiveDayViewModel,
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val spacing = MaterialTheme.spacing

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.archive_title),
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            painter = painterResource(R.drawable.arrow_back),
                            contentDescription = stringResource(id = R.string.common_back)
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            when (val state = uiState) {
                is ArchiveUiState.Loading -> {
                    CircularProgressIndicator(modifier = Modifier.getCenterModifier())
                }
                is ArchiveUiState.Empty -> {
                    ArchiveEmptyState(modifier = Modifier.getCenterModifier())
                }
                is ArchiveUiState.Success -> {
                    ArchiveList(
                        days = state.days,
                        onDayClick = onDayClick,
                        spacing = spacing.medium
                    )
                }
            }
        }
    }
}

@Composable
private fun ArchiveList(
    days: List<DayProgressUiData>,
    onDayClick: (Long) -> Unit,
    spacing: androidx.compose.ui.unit.Dp
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(spacing),
        verticalArrangement = Arrangement.spacedBy(spacing)
    ) {
        items(
            items = days,
            key = { it.dateTimestamp }
        ) { day ->
            val dateLabel = when (val dateText = day.displayDate) {
                is TaskDateUtils.DateText.Resource -> stringResource(id = dateText.resId)
                is TaskDateUtils.DateText.Dynamic -> dateText.text
            }

            FocusTaskCard(
                title = dateLabel,
                completedCount = day.completedCount,
                totalCount = day.totalCount,
                progress = day.progress,
                isFullMode = true,
                modifier = Modifier.clickable { onDayClick(day.dateTimestamp) }
            )
        }
    }
}

@Composable
private fun ArchiveEmptyState(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Your archive is empty yet",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

private fun Modifier.getCenterModifier(): Modifier = this.fillMaxSize().wrapContentSize(Alignment.Center)