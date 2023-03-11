package dev.mfayaz.jcmake.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DefaultHeader(label: String, level: Int) {
  Text(
    modifier = Modifier.padding(start = getNestedLevelPadding(level), top = 16.dp),
    text = label,
    style = TextStyle(
      fontSize = 20.sp,
      fontWeight = FontWeight.Bold,
    )
  )
}