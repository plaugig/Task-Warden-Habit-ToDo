package com.example.taskwardenhabittodo.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

private val PremiumDarkColorScheme = darkColorScheme(
    primary = ElectricIndigo,
    secondary = MutedIndigo,
    tertiary = NeonEmerald,
    background = RawCharcoal,
    surface = SlateBackground,
    error = WarningRed,
    onPrimary = TextOnAccent,
    onBackground = TextHighEmphasis,
    onSurface = TextHighEmphasis,
    onSurfaceVariant = TextMedEmphasis,
    outline = StrokeGrey,
    outlineVariant = DeepSlate
)

@Immutable
data class ExtendedColors(
    val fireStreak: Color,
    val masterStar: Color,
    val containerColor: Color
)

val LocalExtendedColors = staticCompositionLocalOf {
    ExtendedColors(
        fireStreak = Color.Unspecified,
        masterStar = Color.Unspecified,
        containerColor = Color.Unspecified
    )
}

val MaterialTheme.extendedColors: ExtendedColors
    @Composable
    @ReadOnlyComposable
    get() = LocalExtendedColors.current

@Composable
fun TaskWardenHabitToDoTheme(
    content: @Composable () -> Unit
) {

    val extendedColors = ExtendedColors(
        fireStreak = AmberGold,
        masterStar = SoftPurple,
        containerColor = ContainerColor
    )

    CompositionLocalProvider(
        LocalExtendedColors provides extendedColors,
        LocalSpacing provides Spacing()
    ) {
        MaterialTheme(
            colorScheme = PremiumDarkColorScheme,
            typography = Typography,
            content = content
        )
    }
}



