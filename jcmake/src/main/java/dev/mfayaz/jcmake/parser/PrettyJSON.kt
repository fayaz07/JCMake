package dev.mfayaz.jcmake.parser

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.mfayaz.jcmake.parser.utils.conditionalComma
import dev.mfayaz.jcmake.parser.utils.getClosingBraces
import dev.mfayaz.jcmake.parser.utils.getRootText
import dev.mfayaz.jcmake.style.COLOR_BLUE
import dev.mfayaz.jcmake.style.COLOR_GREEN
import dev.mfayaz.jcmake.style.COLOR_ORANGE
import dev.mfayaz.jcmake.style.COLOR_SKYBLUE
import java.math.BigDecimal
import org.json.JSONObject

@Composable
fun PrettyJSON(
  jsonUiParser: JSONUIParser
) {
  BuildJSONUI(jsonUiParser.rootJsonObject)
}

@Composable
private fun BuildJSONUI(root: JSONObject) {
  BuildNestedJSON(rootKey = "", json = root, level = 1, hasNext = false)
}

@Composable
private fun BuildNestedJSON(rootKey: String, json: JSONObject, level: Int, hasNext: Boolean) {
  val keys = json.keys()
  Text(
    text = getRootText(rootKey),
    modifier = Modifier.padding(start = ((level - 1) * 8).dp)
  )
  while (keys.hasNext()) {
    BuildKeyValuePair(keys.next(), json, level, keys.hasNext())
  }
  Text(
    text = getClosingBraces(hasNext),
    modifier = Modifier.padding(start = ((level - 1) * 8).dp)
  )
}

@Composable
private fun BuildKeyValuePair(key: String, json: JSONObject, level: Int, hasNext: Boolean) {
  if (json.get(key) is JSONObject) {
    BuildNestedJSON(
      rootKey = key,
      json = json.getJSONObject(key),
      level = level + 1,
      hasNext = hasNext
    )
  } else {
    Row(
      modifier = Modifier.padding(start = (level * 8).dp)
    ) {
      Text(text = "\"$key\": ")
      Spacer(modifier = Modifier.padding(vertical = 4.dp))
      GetValueTextView(key = key, currJsonObject = json, hasNext = hasNext)
    }
  }
}

@Composable
private fun GetValueTextView(
  key: String,
  currJsonObject: JSONObject,
  hasNext: Boolean
) {
  var color = COLOR_GREEN
  val value = when (currJsonObject.get(key)) {
    is String -> {
      color = COLOR_GREEN
      "\"${currJsonObject.get(key)}\""
    }
    is Boolean -> {
      color = COLOR_ORANGE
      currJsonObject.get(key)
    }
    is Int -> {
      color = COLOR_SKYBLUE
      currJsonObject.get(key)
    }
    is Long -> {
      color = COLOR_BLUE
      currJsonObject.get(key)
    }
    is Float -> {
      color = COLOR_BLUE
      currJsonObject.get(key)
    }
    is Double -> {
      color = COLOR_BLUE
      currJsonObject.get(key)
    }
    is BigDecimal -> {
      color = COLOR_BLUE
      currJsonObject.get(key)
    }
    else -> currJsonObject.get(key)
  }
  Text(text = "${value}${conditionalComma(hasNext)}", color = color)
}
