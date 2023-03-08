package dev.mfayaz.jcmake.parser

import androidx.compose.runtime.Composable
import dev.mfayaz.jcmake.exceptions.InvalidJSONException
import dev.mfayaz.jcmake.exceptions.JSONParseException
import dev.mfayaz.jcmake.ui.DefaultTextField
import java.math.BigDecimal
import org.json.JSONObject

class JSONUIParser(jsonString: String) {
  private lateinit var jsonObject: JSONObject

  init {
    validateJson(jsonString)
  }

  private fun validateJson(jsonString: String) {
    if (jsonString.isEmpty()) throw InvalidJSONException(jsonString)

    try {
      jsonObject = JSONObject(jsonString)
    } catch (e: Exception) {
      throw JSONParseException(e.stackTraceToString())
    }
    if (jsonObject.length() == 0) {
      throw InvalidJSONException("Empty JSON, no key-value pairs exist $jsonString");
    }
  }

  fun typeOf(key: String): FieldType {

    return when (jsonObject.get(key)) {
      is String -> FieldType.String
      is Boolean -> FieldType.Boolean

      is Int -> FieldType.Long
      is Long -> FieldType.Long

      is Float -> FieldType.Double
      is Double -> FieldType.Double
      is BigDecimal -> FieldType.Double

      else -> FieldType.Object
    }
  }

  @Composable
  fun Fill(onDataChange: (String, String) -> Unit) {
    for (key in jsonObject.keys()) {
      DefaultTextField(label = key, value = jsonObject.getString(key), onChange = onDataChange)
    }
  }
}
