package com.example.taskwardenhabittodo.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.taskwardenhabittodo.R
import com.example.taskwardenhabittodo.ui.theme.DeepSlate
import com.example.taskwardenhabittodo.ui.theme.ElectricIndigo
import com.example.taskwardenhabittodo.ui.theme.StrokeGrey
import com.example.taskwardenhabittodo.ui.theme.TextHighEmphasis
import com.example.taskwardenhabittodo.ui.theme.spacing

@Composable
fun HistoryArchiveCard(
    progress: Float,
    modifier: Modifier = Modifier
) {
    val spacing = MaterialTheme.spacing

    if (progress > 0.05f) {
        Surface(
            modifier = modifier.fillMaxSize(),
            shape = RoundedCornerShape(24.dp),
            color = DeepSlate,
            border = BorderStroke(1.dp, StrokeGrey)
        ) {
            Row(
                modifier = Modifier.padding(horizontal = spacing.medium),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.history),
                    contentDescription = null,
                    tint = ElectricIndigo,
                    modifier = Modifier.graphicsLayer {
                        scaleX = 0.8f + (progress * 0.2f)
                        scaleY = 0.8f + (progress * 0.2f)
                    }
                )
                Spacer(modifier = Modifier.width(spacing.small))
                Text(
                    text = stringResource(R.string.task_history_title),
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold
                    ),
                    color = TextHighEmphasis.copy(alpha = progress)
                )
            }
        }
    }
}