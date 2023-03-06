package dev.mfayaz.jcmake.parser

import androidx.compose.runtime.Composable
import dev.mfayaz.jcmake.ui.DefaultTextField
import org.json.JSONObject

class JSONUIParser(private val jsonString: String) {
  private var jsonObject: JSONObject = JSONObject(jsonString)

  @Composable
  fun Fill(onDataChange: (String, String) -> Unit) {
    for (key in jsonObject.keys()) {
      DefaultTextField(label = key, value = jsonObject.getString(key), onChange = onDataChange)
    }
  }
}
