package com.example.taskwardenhabittodo.presentation.cat

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.taskwardenhabittodo.R
import com.example.taskwardenhabittodo.presentation.cat.item.CatUiState
import com.example.taskwardenhabittodo.ui.components.cat.CatActionButton
import com.example.taskwardenhabittodo.ui.components.cat.CatStatsCard
import com.example.taskwardenhabittodo.ui.theme.extendedColors

@Composable
fun CatScreen(
    uiState: CatUiState,
    onFeedClick: () -> Unit,
    onPlayClick: () -> Unit,
    modifier: Modifier = Modifier.fillMaxSize()
) {
    val scrollState = rememberScrollState()

    val colors = MaterialTheme.colorScheme
    val extendedColors = MaterialTheme.extendedColors

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(colors.background)
    ) {

        Spacer(modifier = Modifier.height(24.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1.4f)
                .padding(horizontal = 6.dp)
                .background(
                    color = colors.surface,
                    shape = RoundedCornerShape(24.dp)
                )
                .clip(RoundedCornerShape(24.dp))
        ) {
            Image(
                painter = painterResource(id = uiState.backgroundRes),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize(),

                contentScale = ContentScale.Crop
            )

            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Crossfade(
                    targetState = uiState.catImageRes,
                    animationSpec = tween(durationMillis = 400),
                    label = "CatAnimation"
                ) { targetCatRes ->
                    Image(
                        painter = painterResource(id = targetCatRes),
                        contentDescription = stringResource(R.string.cat_content_description),
                        modifier = Modifier
                            .size(180.dp)
                            .offset(y = 40.dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(horizontal = 16.dp)
                .verticalScroll(scrollState),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            CatStatsCard(
                stats = uiState.stats,
                modifier = Modifier.fillMaxWidth()
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                CatActionButton(
                    title = stringResource(R.string.action_feed),
                    subtitle = stringResource(R.string.action_feed_sub),
                    iconRes = R.drawable.food,
                    accentColor = extendedColors.habitColorTeal,
                    iconBgColor = colors.secondary,
                    onClick = onFeedClick,
                    modifier = Modifier.weight(1f)
                )

                CatActionButton(
                    title = stringResource(R.string.action_play),
                    subtitle = stringResource(R.string.action_play_sub),
                    iconRes = R.drawable.hand,
                    accentColor = extendedColors.habitColorPurple,
                    iconBgColor = colors.secondary,
                    onClick = onPlayClick,
                    modifier = Modifier.weight(1f)
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                CatActionButton(
                    title = stringResource(R.string.action_pet),
                    subtitle = stringResource(R.string.action_pet_sub),
                    iconRes = R.drawable.hand,
                    accentColor = extendedColors.habitColorPink,
                    iconBgColor = colors.secondary,
                    onClick = {},
                    modifier = Modifier.weight(1f)
                )

                CatActionButton(
                    title = stringResource(R.string.action_talk),
                    subtitle = stringResource(R.string.action_talk_sub),
                    iconRes = R.drawable.history,
                    accentColor = extendedColors.habitColorRed,
                    iconBgColor = colors.secondary,
                    onClick = {},
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}
