package dev.mfayaz.jcmake.parser.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.input.ImeAction
import dev.mfayaz.jcmake.model.json.KEY_VALUE
import dev.mfayaz.jcmake.ui.DefaultHeader
import org.json.JSONObject

@Composable
fun FillJSONObjectWithComposables(
  onDataChange: (String, Any) -> Unit,
  currJsonObject: JSONObject,
  level: Int,
  header: String,
  rootKey: String
) {
  val iterator = currJsonObject.keys()
  DefaultHeader(label = header, level = level)
  while (iterator.hasNext()) {
    val key = iterator.next()
    GetComposable(
      key = key,
      onDataChange = { k, v, isTextFieldObject ->
        onDataChange(rootKey.plus(".").plus(k), v)
        if (currJsonObject.has(k)) {
          if (isTextFieldObject) {
            val textFieldObject = currJsonObject.getJSONObject(k)
            textFieldObject.put(KEY_VALUE, v)
            currJsonObject.put(k, textFieldObject)
          } else {
            currJsonObject.put(k, v)
          }
        }
      },
      imeAction = getImeAction(iterator.hasNext()),
      currJsonObject = currJsonObject,
      level = level,
      rootKey = rootKey
    )
  }
}

private fun getImeAction(hasNext: Boolean): ImeAction {
  return if (hasNext) {
    ImeAction.Next
  } else {
    ImeAction.Done
  }
}
