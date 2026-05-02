package com.example.taskwardenhabittodo.ui.components.habit

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.example.taskwardenhabittodo.R
import com.example.taskwardenhabittodo.ui.components.ActionIconButton
import com.example.taskwardenhabittodo.ui.theme.spacing


@Composable
fun HabitsSectionHeader(
    onAddClick: () -> Unit = {},
    modifier: Modifier = Modifier
){
    val spacing = MaterialTheme.spacing

    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(R.string.todays_habits),
            color = MaterialTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
        )

        ActionIconButton(
            iconRes = R.drawable.add,
            onClick = onAddClick,
            isSelected = true
        )


    }
}