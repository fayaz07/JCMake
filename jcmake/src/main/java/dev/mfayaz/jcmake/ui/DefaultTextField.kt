package dev.mfayaz.jcmake.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DefaultTextField(
  label: String,
  value: String,
  onChange: (String, String) -> Unit,
  keyboardType: KeyboardType = KeyboardType.Text
) {
  var state by remember { mutableStateOf(value) }
  OutlinedTextField(
    modifier = Modifier
      .fillMaxWidth()
      .padding(top = 8.dp),
    keyboardOptions = KeyboardOptions(
      keyboardType = keyboardType
    ),
    value = state,
    onValueChange = {
      state = it
      onChange(label, it)
    },
    label = { Text(text = label) }
  )
}