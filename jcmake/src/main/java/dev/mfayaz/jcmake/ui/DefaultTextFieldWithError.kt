package dev.mfayaz.jcmake.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import dev.mfayaz.jcmake.style.COLOR_ERROR

@Composable
fun DefaultTextFieldWithError(
  label: String,
  value: String,
  onChange: (String, String) -> Unit,
  keyboardType: KeyboardType = KeyboardType.Text,
  error: String = "",
  imeAction: ImeAction,
  level: Int
) {
  Column {
    DefaultTextField(
      label = label,
      value = value,
      onChange = onChange,
      keyboardType = keyboardType,
      imeAction = imeAction,
      level = level
    )
    if (error.isNotEmpty()) {
      Text(
        modifier = Modifier.padding(top = 4.dp, start = 8.dp),
        text = error,
        style = TextStyle(
          color = COLOR_ERROR
        )
      )
    }
  }
}
