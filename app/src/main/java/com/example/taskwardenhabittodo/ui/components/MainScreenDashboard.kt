package com.example.taskwardenhabittodo.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.taskwardenhabittodo.R
import com.example.taskwardenhabittodo.ui.theme.getCurrentFormattedDate
import com.example.taskwardenhabittodo.ui.theme.spacing


@Composable
fun MainScreenDashboard(
    modifier: Modifier = Modifier
){
    val spacing = MaterialTheme.spacing

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = spacing.medium)
    ) {
        Text(
            text = stringResource(R.string.dashboard_title),
            style = MaterialTheme.typography.headlineMedium.copy(
                fontWeight = FontWeight.ExtraBold,
                letterSpacing = (0.5).sp
            ),
            color = MaterialTheme.colorScheme.onBackground
        )

        Text(
            text = getCurrentFormattedDate(),
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant.copy(0.7f)
        )
    }
}