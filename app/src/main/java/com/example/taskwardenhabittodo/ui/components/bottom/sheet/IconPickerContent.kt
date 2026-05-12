package com.example.taskwardenhabittodo.ui.components.bottom.sheet

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.taskwardenhabittodo.domain.item.CategoryType
import com.example.taskwardenhabittodo.ui.theme.spacing
import androidx.compose.material3.Icon
import androidx.compose.foundation.lazy.grid.items

@Composable
fun IconPickerContent(onCategorySelected: (CategoryType) -> Unit ){

    val spacing = MaterialTheme.spacing

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(spacing.medium)
            .navigationBarsPadding()
    ) {
        Text(
            text = "Select Icon",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = spacing.medium)
        )

        androidx.compose.foundation.lazy.grid.LazyVerticalGrid(
            columns = androidx.compose.foundation.lazy.grid.GridCells.Fixed(4),
            horizontalArrangement = Arrangement.spacedBy(spacing.small),
            verticalArrangement = Arrangement.spacedBy(spacing.small)
        ) {
            items(CategoryType.entries) { category ->
                IconButton(
                    onClick = { onCategorySelected(category) },
                    modifier = Modifier
                        .clip(RoundedCornerShape(12.dp))
                        .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f))
                        .padding(8.dp)
                ) {
                    Icon(
                        painter = painterResource(id = category.iconResId),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(spacing.large))
    }
}