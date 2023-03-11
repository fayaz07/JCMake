package dev.mfayaz.jcmake.parser

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import dev.mfayaz.jcmake.exceptions.InvalidJSONException
import dev.mfayaz.jcmake.exceptions.JSONParseException
import dev.mfayaz.jcmake.model.json.KEY_VALUE
import dev.mfayaz.jcmake.model.mapper.TextFieldModelMapper
import dev.mfayaz.jcmake.ui.DefaultHeader
import dev.mfayaz.jcmake.ui.DefaultSwitch
import dev.mfayaz.jcmake.ui.DefaultTextField
import dev.mfayaz.jcmake.ui.DefaultTextFieldWithError
import java.math.BigDecimal
import org.json.JSONObject

class JSONUIParser(jsonString: String) {
  private lateinit var rootJsonObject: JSONObject

  init {
    initialise(jsonString)
  }

  private fun initialise(jsonString: String) {
    if (jsonString.isEmpty()) throw InvalidJSONException(jsonString)

    try {
      rootJsonObject = JSONObject(jsonString)
    } catch (e: Exception) {
      throw JSONParseException(e.stackTraceToString())
    }
    if (rootJsonObject.length() == 0) {
      throw InvalidJSONException("Empty JSON, no key-value pairs exist $jsonString")
    }
  }

  fun isTextFieldObject(key: String, currJsonObject: JSONObject): Boolean {
    val nestedObject = currJsonObject.getJSONObject(key)
    return nestedObject.has("value")
  }

  fun typeOf(key: String, currJsonObject: JSONObject): FieldType {
    return when (currJsonObject.get(key)) {
      is String -> FieldType.String
      is Boolean -> FieldType.Boolean
      is Int -> FieldType.Long
      is Long -> FieldType.Long
      is Float -> FieldType.Double
      is Double -> FieldType.Double
      is BigDecimal -> FieldType.Double
      else -> {
        if (isTextFieldObject(key, currJsonObject)) {
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
  fun GetComposable(
    key: String,
    onDataChange: (String, Any) -> Unit,
    imeAction: ImeAction,
    currJsonObject: JSONObject,
    level: Int
  ) {
    when (typeOf(key, currJsonObject)) {
      FieldType.String -> DefaultTextField(
        label = key,
        value = currJsonObject.getString(key),
        onChange = onDataChange,
        imeAction = imeAction,
        level = level
      )
      FieldType.Integer -> DefaultTextField(
        label = key,
        value = currJsonObject.getInt(key).toString(),
        onChange = { k, v ->
          onDataChange(k, parseInt(v))
        },
        keyboardType = KeyboardType.Number,
        imeAction = imeAction,
        level = level
      )
      FieldType.Long -> DefaultTextField(
        label = key,
        value = currJsonObject.getLong(key).toString(),
        onChange = { k, v ->
          onDataChange(k, parseLong(v))
        },
        keyboardType = KeyboardType.Number,
        imeAction = imeAction,
        level = level
      )
      FieldType.Double -> DefaultTextField(
        label = key,
        value = currJsonObject.getString(key),
        onChange = { k, v ->
          onDataChange(k, parseDouble(v))
        },
        keyboardType = KeyboardType.Number,
        imeAction = imeAction,
        level = level
      )
      FieldType.Boolean -> DefaultSwitch(
        label = key,
        value = currJsonObject.getBoolean(key),
        onChange = onDataChange,
        level = level
      )
      FieldType.TextFieldObject -> {
        val nestedJSONObject = currJsonObject.getJSONObject(key)
        val textFieldObject = TextFieldModelMapper.parseFromJson(key, nestedJSONObject)
        DefaultTextFieldWithError(
          label = textFieldObject.label,
          value = textFieldObject.value,
          onChange = onDataChange,
          error = textFieldObject.error ?: "",
          keyboardType = getKeyboardTypeFromValue(KEY_VALUE, nestedJSONObject),
          imeAction = imeAction,
          level = level
        )
      }
      else -> {
        if (currJsonObject.get(key) is JSONObject) {
          GenerateComposables(
            onDataChange = onDataChange,
            currJsonObject = currJsonObject.getJSONObject(key),
            level = level + 1,
            header = key
          )
        } else {
          Text(text = currJsonObject.get(key).toString())
        }
      }
//      FieldType.Object -> TODO()
    }
  }

  @Composable
  private fun GenerateComposables(
    onDataChange: (String, Any) -> Unit,
    currJsonObject: JSONObject,
    level: Int,
    header: String
  ) {
    val iterator = currJsonObject.keys()
    DefaultHeader(label = header, level = level)
    while (iterator.hasNext()) {
      val key = iterator.next()
      println(key)
      GetComposable(
        key = key,
        onDataChange = onDataChange,
        imeAction = getImeAction(iterator.hasNext()),
        currJsonObject = currJsonObject,
        level = level
      )
    }
  }

  @Composable
  fun Fill(onDataChange: (String, Any) -> Unit) {
    GenerateComposables(
      onDataChange,
      rootJsonObject,
      1,
      "Root"
    )
  }

  private fun getImeAction(hasNext: Boolean): ImeAction {
    return if (hasNext) {
      ImeAction.Next
    } else {
      ImeAction.Done
    }
  }
}
