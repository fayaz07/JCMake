package dev.mfayaz.jcmake.ui

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

fun getNestedLevelPadding(level: Int): Dp {
  return (level * 4).coerceAtMost(24).dp
}
