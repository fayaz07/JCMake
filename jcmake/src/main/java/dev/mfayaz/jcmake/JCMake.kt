package dev.mfayaz.jcmake

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import org.json.JSONObject

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MakeUI2(json: String, onDataChange: (String, String) -> Unit) {
  val jsonObject = JSONObject(json)

  Column {
    for (key in jsonObject.keys()) {
      var tt by remember { mutableStateOf(jsonObject[key].toString()) }
      OutlinedTextField(
        value = tt,
        onValueChange = {
          tt = it
          onDataChange(key, it)
        },
        label = { Text(text = key) }
      )
    }
  }
}
