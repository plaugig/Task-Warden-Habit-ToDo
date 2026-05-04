package com.example.taskwardenhabittodo.ui.components.bottom.sheet

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import com.example.taskwardenhabittodo.domain.ActionType
import com.example.taskwardenhabittodo.domain.CategoryType
import com.example.taskwardenhabittodo.domain.Priority
import com.example.taskwardenhabittodo.ui.components.PriorityButton
import com.example.taskwardenhabittodo.ui.components.TaskPropertyCard
import com.example.taskwardenhabittodo.ui.theme.AmberGold
import com.example.taskwardenhabittodo.ui.theme.SuccessGreen
import com.example.taskwardenhabittodo.ui.theme.TaskWardenHabitToDoTheme
import com.example.taskwardenhabittodo.ui.theme.WarningRed
import com.example.taskwardenhabittodo.ui.theme.extendedColors
import com.example.taskwardenhabittodo.ui.theme.spacing

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetScreen(
    type: ActionType,
    onDismiss: () -> Unit,
    onCreateClick: (String, String, Int, CategoryType) -> Unit
) {
    val extendedColors = MaterialTheme.extendedColors
    val colorScheme = MaterialTheme.colorScheme

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        containerColor = extendedColors.containerColor,
        dragHandle = { BottomSheetDefaults.DragHandle(color = colorScheme.outline) }
    ) {
        BottomSheetContent(
            type = type,
            onDismiss = onDismiss,
            onCreateClick = onCreateClick,

        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetContent(
    type: ActionType,
    onDismiss: () -> Unit,
    onCreateClick: (String, String, Int, CategoryType ) -> Unit
){
    val spacing = MaterialTheme.spacing
    val colorScheme = MaterialTheme.colorScheme
    val extendedColors = MaterialTheme.extendedColors

    var title by remember { mutableStateOf("") }
    var selectedTime by remember { mutableStateOf("12:00 PM") }
    var repeatCount by remember { mutableIntStateOf(1) }
    var selectedPriority by remember { mutableStateOf(Priority.MEDIUM) }
    var selectedCategory by remember { mutableStateOf(CategoryType.HEALTH) }
    var showIconPicer by remember { mutableStateOf(false) }

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
                    stringResource(R.string.new_habit)
                else
                    stringResource(R.string.new_task),
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
                stringResource(R.string.habit_name_label)
            else
                stringResource(R.string.task_name_label),
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
                    stringResource(R.string.placeholder_name),
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
                label = stringResource(R.string.label_time),
                value = selectedTime,
                onClick = {},
                modifier = Modifier.weight(1f)
            )

            if (type == ActionType.HABIT) {
                Spacer(modifier = Modifier.width(spacing.small))
                TaskPropertyCard(
                    iconRes = selectedCategory.iconResId,
                    label = stringResource(R.string.label_category),
                    value = selectedCategory.name,
                    onClick = { showIconPicer = true },
                    modifier = Modifier.weight(1f)
                )
            }
        }

        if (type == ActionType.TASK) {
            Spacer(modifier = Modifier.height(spacing.medium))
            Text(
                text = stringResource(R.string.label_priority).uppercase(),
                style = MaterialTheme.typography.labelMedium,
                color = colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.height(spacing.small))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(spacing.small)
            ) {
                PriorityButton(
                    label = "High",
                    priorityColor = WarningRed,
                    isSelected = selectedPriority == Priority.HIGH,
                    onClick = { selectedPriority = Priority.HIGH },
                    modifier = Modifier.weight(1f)
                )
                PriorityButton(
                    label = "Medium",
                    priorityColor = AmberGold,
                    isSelected = selectedPriority == Priority.MEDIUM,
                    onClick = { selectedPriority = Priority.MEDIUM },
                    modifier = Modifier.weight(1f)
                )
                PriorityButton(
                    label = "Low",
                    priorityColor = SuccessGreen,
                    isSelected = selectedPriority == Priority.LOW,
                    onClick = { selectedPriority = Priority.LOW },
                    modifier = Modifier.weight(1f)
                )
            }
        }

        if (type == ActionType.HABIT) {
            Spacer(modifier = Modifier.height(spacing.medium))
            Text(
                text = stringResource(R.string.repeat_count_label),
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
                    Icon(
                        painterResource(R.drawable.remove),
                        null,
                        tint = colorScheme.primary
                    )
                }
                Text(
                    text = "$repeatCount " + stringResource(R.string.times),
                    style = MaterialTheme.typography.titleMedium,
                    color = colorScheme.onSurface
                )
                IconButton(onClick = { repeatCount++ }) {
                    Icon(
                        painterResource(R.drawable.add),
                        null,
                        tint = colorScheme.primary
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(spacing.large))

        Button(
            onClick = {
                onCreateClick(
                    title,
                    selectedTime,
                    repeatCount,
                    selectedCategory,
                )
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
        )
        {
            Icon(painterResource(R.drawable.check), null)
            Spacer(modifier = Modifier.width(spacing.small))
            Text(
                text = if (type == ActionType.HABIT)
                    stringResource(R.string.btn_create_habit)
                else
                    stringResource(R.string.btn_create_task),
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
            )
        }
        Spacer(modifier = Modifier.height(spacing.medium))

        if (showIconPicer){
            ModalBottomSheet(
                onDismissRequest = {
                    showIconPicer = false
                },
                contentColor = extendedColors.containerColor
            ) {
                IconPickerContent(
                    onCategorySelected = { category ->
                        selectedCategory = category
                        showIconPicer = false
                    }
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
                onCreateClick = { _, _, _ , _-> }
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
                onCreateClick = { _, _, _ , _-> }
            )
        }
    }
}
