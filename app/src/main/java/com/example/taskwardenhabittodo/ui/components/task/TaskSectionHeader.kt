package com.example.taskwardenhabittodo.ui.components.task

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.taskwardenhabittodo.ui.theme.AmberGold

@Composable
fun TaskSectionHeader(
    title: String,
    iconRes: Int,
    doneCount: Int,
    totalCount: Int
) {
    val colorScheme = MaterialTheme.colorScheme
    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(iconRes),
            contentDescription = null,
            tint = AmberGold,
            modifier = Modifier.size(20.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
        )
        Box(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 16.dp)
                .height(1.dp)
                .background(colorScheme.outline.copy(alpha = 0.2f))
        )
        Text(
            text = "$doneCount/$totalCount done",
            style = MaterialTheme.typography.labelMedium,
            color = colorScheme.onSurfaceVariant
        )
    }
}

