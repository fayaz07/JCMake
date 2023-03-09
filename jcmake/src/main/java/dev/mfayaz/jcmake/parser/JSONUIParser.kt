package dev.mfayaz.jcmake.parser

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.input.KeyboardType
import dev.mfayaz.jcmake.exceptions.InvalidJSONException
import dev.mfayaz.jcmake.exceptions.JSONParseException
import dev.mfayaz.jcmake.model.json.KEY_VALUE
import dev.mfayaz.jcmake.model.mapper.TextFieldModelMapper
import dev.mfayaz.jcmake.ui.DefaultSwitch
import dev.mfayaz.jcmake.ui.DefaultTextField
import dev.mfayaz.jcmake.ui.DefaultTextFieldWithError
import java.math.BigDecimal
import org.json.JSONObject

class JSONUIParser(jsonString: String) {
  private lateinit var jsonObject: JSONObject

  init {
    initialise(jsonString)
  }

  private fun initialise(jsonString: String) {
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

  fun isTextFieldObject(key: String): Boolean {
    val nestedObject = jsonObject.getJSONObject(key)
    return nestedObject.has("value")
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
      else -> {
        if (isTextFieldObject(key)) {
          FieldType.TextFieldObject
        } else {
          FieldType.Object
        }
      }
    }
  }

  fun parseDouble(value: String): Double {
    return value.toDoubleOrNull() ?: 0.0
  }

  fun parseInt(value: String): Int {
    return value.toIntOrNull() ?: 0
  }

  fun parseLong(value: String): Long {
    return value.toLongOrNull() ?: 0
  }

  fun getKeyboardTypeFromValue(key: String, jsonObj: JSONObject): KeyboardType {
    return when (jsonObj.get(key)) {
      is String -> KeyboardType.Text
      is Int -> KeyboardType.Number
      is Long -> KeyboardType.Number
      is Float -> KeyboardType.Number
      is Double -> KeyboardType.Number
      is BigDecimal -> KeyboardType.Number
      else -> {
        KeyboardType.Text
      }
    }
  }

  @Composable
  fun GetTextField(key: String, onDataChange: (String, Any) -> Unit) {
    when (typeOf(key)) {
      FieldType.String -> DefaultTextField(
        label = key,
        value = jsonObject.getString(key),
        onChange = onDataChange
      )
      FieldType.Integer -> DefaultTextField(
        label = key,
        value = jsonObject.getInt(key).toString(),
        onChange = { k, v ->
          onDataChange(k, parseInt(v))
        },
        keyboardType = KeyboardType.Number
      )
      FieldType.Long -> DefaultTextField(
        label = key,
        value = jsonObject.getLong(key).toString(),
        onChange = { k, v ->
          onDataChange(k, parseLong(v))
        },
        keyboardType = KeyboardType.Number
      )
      FieldType.Double -> DefaultTextField(
        label = key,
        value = jsonObject.getString(key),
        onChange = { k, v ->
          onDataChange(k, parseDouble(v))
        },
        keyboardType = KeyboardType.Number
      )
      FieldType.Boolean -> DefaultSwitch(
        label = key,
        value = jsonObject.getBoolean(key),
        onChange = onDataChange
      )
      FieldType.TextFieldObject -> {
        val nestedJSONObject = jsonObject.getJSONObject(key)
        val textFieldObject = TextFieldModelMapper.parseFromJson(key, nestedJSONObject)
        DefaultTextFieldWithError(
          label = textFieldObject.label,
          value = textFieldObject.value,
          onChange = onDataChange,
          error = textFieldObject.error ?: "",
          keyboardType = getKeyboardTypeFromValue(KEY_VALUE, nestedJSONObject)
        )
      }
      else -> {
        Text(text = key)
      }
//      FieldType.Object -> TODO()
    }
  }

  @Composable
  fun Fill(onDataChange: (String, Any) -> Unit) {
    for (key in jsonObject.keys()) {
      GetTextField(key = key, onDataChange = onDataChange)
    }
  }
}
