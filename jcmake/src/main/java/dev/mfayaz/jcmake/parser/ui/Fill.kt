package dev.mfayaz.jcmake.parser.ui

import androidx.compose.runtime.Composable
import org.json.JSONObject

@Composable
fun Fill(
  jsonObj: JSONObject,
  onDataChange: (String, Any) -> Unit
) {
  FillJSONObjectWithComposables(
    onDataChange = onDataChange,
    currJsonObject = jsonObj,
    level = 1,
    header = "Root",
    rootKey = ""
  )
}
