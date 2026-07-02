package com.example.taskwardenhabittodo.presentation.cat

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.taskwardenhabittodo.R
import com.example.taskwardenhabittodo.domain.pet.enums.CatActionType
import com.example.taskwardenhabittodo.domain.pet.enums.CatMood
import com.example.taskwardenhabittodo.presentation.cat.item.CatUiState
import com.example.taskwardenhabittodo.presentation.cat.item.toCatImageRes
import com.example.taskwardenhabittodo.presentation.cat.item.toStatBars
import com.example.taskwardenhabittodo.ui.components.cat.CatActionButton
import com.example.taskwardenhabittodo.ui.components.cat.CatStatActionCard
import com.example.taskwardenhabittodo.ui.components.cat.CatStatsCard
import com.example.taskwardenhabittodo.ui.theme.extendedColors

@Composable
fun CatScreen(
    uiState: CatUiState,
    onAction: (CatActionType) -> Unit,
    modifier: Modifier = Modifier.fillMaxSize()
) {
    val scrollState = rememberScrollState()
    val colors = MaterialTheme.colorScheme
    val ext = MaterialTheme.extendedColors

    val cat = uiState.cat
    val catRes = cat?.let { CatMood.fromStats(it).toCatImageRes() } ?: R.drawable.cat_neutral_1


//    val actions = listOf(
//        CatActionSpec(CatActionType.FEED,
//            R.string.cat_action_feed,
//            R.drawable.food,
//            ext.statFull),
//        CatActionSpec(CatActionType.WATER,
//            R.string.cat_action_water,
//            R.drawable.drink,
//            ext.statThirst),
//        CatActionSpec(CatActionType.CLEAN,
//            R.string.cat_action_clean,
//            R.drawable.autorenew,
//            ext.statLitter),
//        CatActionSpec(CatActionType.PET,
//            R.string.cat_action_pet,
//            R.drawable.hand,
//            ext.statMood),
//        CatActionSpec(CatActionType.PLAY,
//            R.string.cat_action_play,
//            R.drawable.animal,
//            ext.habitColorPurple),
//        CatActionSpec(CatActionType.TREAT,
//            R.string.cat_action_treat,
//            R.drawable.bolt,
//            ext.habitColorAmber)
//    )

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
                .clip(RoundedCornerShape(24.dp))
                .background(color = colors.surface, shape = RoundedCornerShape(24.dp))
        ) {
            Image(
                painter = painterResource(id = uiState.backgroundRes),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 26.dp)
                    .size(width = 150.dp, height = 26.dp)
                    .background(Color.Black.copy(alpha = 0.22f), CircleShape)
            )

            Crossfade(
                targetState = catRes,
                animationSpec = tween(durationMillis = 400),
                label = "CatMood",
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .fillMaxHeight(0.66f)
                    .padding(bottom = 30.dp)
            ) { res ->
                Image(
                    painter = painterResource(id = res),
                    contentDescription = stringResource(R.string.cat_content_description),
                    contentScale = ContentScale.Fit,
                    alignment = Alignment.BottomCenter,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = R.drawable.coins),
                contentDescription = null,
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = stringResource(R.string.cat_action_cost, uiState.petPoints),
                color = colors.onSurface
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(horizontal = 16.dp)
                .verticalScroll(scrollState),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            val statBars = uiState.cat?.toStatBars() ?: emptyList()
            statBars.forEach { stat ->
                CatStatActionCard(
                    stat = stat,
                    petPoints = uiState.petPoints,
                    onAction = onAction
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

private data class CatActionSpec(
    val action: CatActionType,
    @StringRes val titleRes: Int,
    @DrawableRes val iconRes: Int,
    val accent: Color
)