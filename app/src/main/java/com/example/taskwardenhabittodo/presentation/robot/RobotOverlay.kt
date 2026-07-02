package com.example.taskwardenhabittodo.presentation.robot

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.taskwardenhabittodo.presentation.robot.item.RobotOverlayState
import kotlinx.coroutines.delay

@Composable
fun RobotOverlay(
    state: RobotOverlayState,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier
) {
    if (!state.visible) return


    var shown by remember(state.fullText) { mutableStateOf(0) }
    LaunchedEffect(state.fullText) {
        shown = 0
        for (i in 1..state.fullText.length) {
            shown = i
            delay(TYPE_SPEED_MS)
        }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectTapGestures { onDismiss() }
            }
    ) {
        Row(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(top = 12.dp, end = 12.dp)
                .widthIn(max = 300.dp),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.End
        ) {

            Box(
                modifier = Modifier
                    .weight(1f, fill = false)
                    .background(Color.White, RoundedCornerShape(16.dp))
                    .padding(horizontal = 14.dp, vertical = 10.dp)
            ) {
                Text(
                    text = state.fullText.take(shown),
                    color = Color(0xFF1A1A1A),
                    fontSize = 14.sp
                )
            }

            Spacer(modifier = Modifier.width(8.dp))


            Image(
                painter = painterResource(state.robotRes),
                contentDescription = null,
                modifier = Modifier.size(140.dp)
            )
        }
    }
}

private const val TYPE_SPEED_MS = 25L