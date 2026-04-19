package com.example.taskwardenhabittodo.ui.theme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

private val PremiumDarkColorScheme = darkColorScheme(
    primary = ElectricIndigo,
    secondary = MutedIndigo,
    tertiary = NeonEmerald,
    error = WarningRed,
    background = RawCharcoal,
    surface = SlateBackground,
    onPrimary = TextOnAccent,
    onBackground = TextHighEmphasis,
    onSurface = TextHighEmphasis,
    onSurfaceVariant = TextMedEmphasis
)

@Composable
fun TaskWardenHabitToDoTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = PremiumDarkColorScheme,
        typography = Typography,
        content = content
    )
}