package dev.mfayaz.jcmake.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DefaultSwitch(
  label: String,
  value: Boolean,
  onChange: (String, Boolean) -> Unit,
  level: Int
) {
  var state by remember { mutableStateOf(value) }
  Row(
    modifier = Modifier
      .fillMaxWidth()
      .padding(top = 8.dp, start = (level * 4).dp)
  ) {
    Text(
      modifier = Modifier
        .fillMaxWidth(0.85f)
        .padding(end = 8.dp)
        .height(48.dp)
        .wrapContentHeight(align = CenterVertically),
      text = label,
      style = TextStyle(
        fontSize = 18.sp
      )
    )
    Switch(
      checked = state,
      onCheckedChange = {
        state = it
        onChange(label, it)
      }
    )
  }
}
