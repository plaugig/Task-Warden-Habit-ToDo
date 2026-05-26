package com.example.taskwardenhabittodo.ui.components.bottom.sheet

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerDefaults
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.taskwardenhabittodo.R
import com.example.taskwardenhabittodo.domain.item.ActionType
import com.example.taskwardenhabittodo.domain.item.CategoryType
import com.example.taskwardenhabittodo.domain.item.Priority
import com.example.taskwardenhabittodo.ui.components.ColorPickerRow
import com.example.taskwardenhabittodo.ui.components.PriorityButton
import com.example.taskwardenhabittodo.ui.components.TaskPropertyCard
import com.example.taskwardenhabittodo.ui.theme.AmberGold
import com.example.taskwardenhabittodo.ui.theme.HabitColorPalette
import com.example.taskwardenhabittodo.ui.theme.HabitColorPurple
import com.example.taskwardenhabittodo.ui.theme.SuccessGreen
import com.example.taskwardenhabittodo.ui.theme.TaskWardenHabitToDoTheme
import com.example.taskwardenhabittodo.ui.theme.WarningRed
import com.example.taskwardenhabittodo.ui.theme.extendedColors
import com.example.taskwardenhabittodo.ui.theme.spacing

data class NewHabitInput(
    val title: String,
    val time: String?,
    val repeatCount: Int,
    val category: CategoryType,
    val colorHex: Long
)

data class NewTaskInput(
    val title: String,
    val time: String?,
    val priority: Priority,
    val colorHex: Long
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetScreen(
    type: ActionType,
    onDismiss: () -> Unit,
    onCreateHabit: (NewHabitInput) -> Unit = {},
    onCreateTask: (NewTaskInput) -> Unit = {}
) {
    val extendedColors = MaterialTheme.extendedColors
    val colorScheme = MaterialTheme.colorScheme

    val sheetState = androidx.compose.material3.rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        containerColor = extendedColors.containerColor,
        dragHandle = { BottomSheetDefaults.DragHandle(color = colorScheme.outline) }
    ) {
        BottomSheetContent(
            type = type,
            onDismiss = onDismiss,
            onCreateHabit = onCreateHabit,
            onCreateTask = onCreateTask
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetContent(
    type: ActionType,
    onDismiss: () -> Unit,
    onCreateHabit: (NewHabitInput) -> Unit = {},
    onCreateTask: (NewTaskInput) -> Unit = {}
) {
    val spacing = MaterialTheme.spacing
    val colorScheme = MaterialTheme.colorScheme
    val extendedColors = MaterialTheme.extendedColors

    var title by remember { mutableStateOf("") }
    var selectedTime by remember { mutableStateOf<String?>(null) }
    var repeatCount by remember { mutableIntStateOf(1) }
    var selectedPriority by remember { mutableStateOf(Priority.MEDIUM) }
    var selectedCategory by remember { mutableStateOf(CategoryType.HEALTH) }
    var selectedColor by remember { mutableStateOf(HabitColorPurple) }
    var showIconPicker by remember { mutableStateOf(false) }
    var showTimePicker by remember { mutableStateOf(false) }

    val timePickerState = rememberTimePickerState(
        initialHour = 12,
        initialMinute = 0,
        is24Hour = true
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(extendedColors.containerColor)
            .padding(spacing.medium)
            .navigationBarsPadding()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = if (type == ActionType.HABIT)
                    stringResource(R.string.habit_new_title)
                else
                    stringResource(R.string.task_new_title),
                style = MaterialTheme.typography.headlineSmall.copy(
                    fontWeight = FontWeight.Bold
                ),
                color = colorScheme.onSurface
            )
            IconButton(onClick = onDismiss) {
                Icon(
                    painterResource(R.drawable.close),
                    contentDescription = null,
                    tint = colorScheme.onSurfaceVariant
                )
            }
        }

        Spacer(modifier = Modifier.height(spacing.medium))

        Text(
            text = if (type == ActionType.HABIT)
                stringResource(R.string.habit_label_name)
            else
                stringResource(R.string.task_label_name),
            style = MaterialTheme.typography.labelMedium,
            color = colorScheme.onSurfaceVariant
        )
        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            placeholder = {
                Text(
                    stringResource(R.string.habit_placeholder),
                    color = colorScheme.onSurfaceVariant.copy(alpha = 0.5f)
                )
            },
            shape = RoundedCornerShape(16.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = colorScheme.primary,
                unfocusedBorderColor = colorScheme.outline,
                focusedContainerColor = colorScheme.surfaceVariant.copy(alpha = 0.3f),
                unfocusedContainerColor = colorScheme.surfaceVariant.copy(alpha = 0.3f),
                cursorColor = colorScheme.primary,
                focusedTextColor = colorScheme.onSurface,
                unfocusedTextColor = colorScheme.onSurface,
                focusedPlaceholderColor = colorScheme.onSurfaceVariant.copy(alpha = 0.5f),
                unfocusedPlaceholderColor = colorScheme.onSurfaceVariant.copy(alpha = 0.5f)
            )
        )

        Spacer(modifier = Modifier.height(spacing.small))

        Row(modifier = Modifier.fillMaxWidth()) {
            TaskPropertyCard(
                iconRes = R.drawable.nest_clock,
                label = stringResource(R.string.prop_label_time),
                value = selectedTime ?: stringResource(R.string.prop_select_time),
                onClick = { showTimePicker = true },
                modifier = Modifier.weight(1f)
            )

            if (type == ActionType.HABIT) {
                Spacer(modifier = Modifier.width(spacing.small))
                TaskPropertyCard(
                    iconRes = selectedCategory.iconResId,
                    label = stringResource(R.string.prop_label_category),
                    value = selectedCategory.name,
                    onClick = { showIconPicker = true },
                    modifier = Modifier.weight(1f)
                )
            }
        }

        if (type == ActionType.TASK) {
            Spacer(modifier = Modifier.height(spacing.medium))
            Text(
                text = stringResource(R.string.prop_label_priority).uppercase(),
                style = MaterialTheme.typography.labelMedium,
                color = colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.height(spacing.small))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(spacing.small)
            ) {
                Priority.entries.filter { it != Priority.NONE }.forEach { priority ->
                    PriorityButton(
                        label = priority.name.lowercase().replaceFirstChar { it.uppercase() },
                        priorityColor = when (priority) {
                            Priority.HIGH -> WarningRed
                            Priority.MEDIUM -> AmberGold
                            Priority.LOW -> SuccessGreen
                            else -> colorScheme.outline
                        },
                        isSelected = selectedPriority == priority,
                        onClick = {
                            selectedPriority = if (selectedPriority == priority) Priority.NONE
                            else priority
                        },
                        modifier = Modifier.weight(1f)
                    )
                }
            }

            Spacer(modifier = Modifier.height(spacing.medium))

            ColorPickerRow(
                selectedColor = selectedColor,
                onColorSelected = { selectedColor = it }
            )
        }

        if (type == ActionType.HABIT) {
            Spacer(modifier = Modifier.height(spacing.medium))
            Text(
                text = stringResource(R.string.habit_repeat_label),
                style = MaterialTheme.typography.labelMedium,
                color = colorScheme.onSurfaceVariant
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(colorScheme.surfaceVariant.copy(alpha = 0.3f))
                    .padding(spacing.small),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { if (repeatCount > 1) repeatCount-- }) {
                    Icon(painterResource(R.drawable.remove), null, tint = colorScheme.primary)
                }
                Text(
                    text = "$repeatCount " + stringResource(R.string.common_times),
                    style = MaterialTheme.typography.titleMedium,
                    color = colorScheme.onSurface
                )
                IconButton(onClick = { repeatCount++ }) {
                    Icon(painterResource(R.drawable.add), null, tint = colorScheme.primary)
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(colorScheme.outline.copy(alpha = 0.3f))
            )

            Spacer(modifier = Modifier.height(spacing.medium))

            Spacer(modifier = Modifier.height(spacing.medium))

            ColorPickerRow(
                selectedColor = selectedColor,
                onColorSelected = { selectedColor = it }
            )

            Spacer(modifier = Modifier.height(spacing.medium))
        }

        Spacer(modifier = Modifier.height(spacing.large))

        Button(
            onClick = {
                if (type == ActionType.HABIT) {
                    onCreateHabit(
                        NewHabitInput(
                            title = title,
                            time = selectedTime,
                            repeatCount = repeatCount,
                            category = selectedCategory,
                            colorHex = selectedColor.value.toLong()
                        )
                    )
                } else {
                    onCreateTask(
                        NewTaskInput(
                            title = title,
                            time = selectedTime,
                            priority = selectedPriority,
                            colorHex = selectedColor.value.toLong()
                        )
                    )
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = colorScheme.primary,
                contentColor = colorScheme.onPrimary
            ),
            enabled = title.isNotBlank()
        ) {
            Icon(painterResource(R.drawable.check), null)
            Spacer(modifier = Modifier.width(spacing.small))
            Text(
                text = if (type == ActionType.HABIT)
                    stringResource(R.string.habit_btn_create)
                else
                    stringResource(R.string.task_btn_create),
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
            )
        }

        Spacer(modifier = Modifier.height(spacing.medium))

        if (showIconPicker) {
            ModalBottomSheet(
                onDismissRequest = { showIconPicker = false },
                contentColor = extendedColors.containerColor
            ) {
                IconPickerContent(
                    onCategorySelected = { category ->
                        selectedCategory = category
                        showIconPicker = false
                    }
                )
            }
        }

        if (showTimePicker) {
            AnimatedTimePickerDialog(
                onDismiss = { showTimePicker = false },
                onConfirm = {
                    val hour = timePickerState.hour
                    val minute = timePickerState.minute
                    selectedTime = String.format("%02d:%02d", hour, minute)
                    showTimePicker = false
                }
            ) {
                TimePicker(
                    state = timePickerState,
                    colors = TimePickerDefaults.colors(
                        clockDialColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
                        selectorColor = MaterialTheme.colorScheme.primary,
                        periodSelectorSelectedContainerColor = MaterialTheme.colorScheme.primaryContainer
                    )
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF0A0A0A)
@Composable
fun PreviewHabitBottomSheet() {
    TaskWardenHabitToDoTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            BottomSheetContent(
                type = ActionType.HABIT,
                onDismiss = {},
                onCreateHabit = {}
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF0A0A0A)
@Composable
fun PreviewTaskBottomSheet() {
    TaskWardenHabitToDoTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            BottomSheetContent(
                type = ActionType.TASK,
                onDismiss = {},
                onCreateTask = {}
            )
        }
    }
}