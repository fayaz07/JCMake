package dev.mfayaz.jcmake

import androidx.compose.foundation.lazy.LazyColumn
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
fun MakeUI(json: String, onDataChange: (String, String) -> Unit) {
  val jsonObject = JSONObject(json)

  LazyColumn {
    item {
      Fill(jsonObject, onDataChange)
    }
  }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Fill(jsonObject: JSONObject, onDataChange: (String, String) -> Unit) {
  for (key in jsonObject.keys()) {
    var state by remember { mutableStateOf(jsonObject[key].toString()) }
    OutlinedTextField(
      value = state,
      onValueChange = {
        state = it
        onDataChange(key, it)
      },
      label = { Text(text = key) }
    )
  }
}
