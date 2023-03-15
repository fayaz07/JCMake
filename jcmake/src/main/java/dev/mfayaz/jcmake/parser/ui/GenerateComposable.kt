package dev.mfayaz.jcmake.parser.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import dev.mfayaz.jcmake.model.json.KEY_VALUE
import dev.mfayaz.jcmake.model.mapper.TextFieldModelMapper
import dev.mfayaz.jcmake.parser.FieldType
import dev.mfayaz.jcmake.parser.parseDouble
import dev.mfayaz.jcmake.parser.parseInt
import dev.mfayaz.jcmake.parser.parseLong
import dev.mfayaz.jcmake.ui.DefaultSwitch
import dev.mfayaz.jcmake.ui.DefaultTextField
import dev.mfayaz.jcmake.ui.DefaultTextFieldWithError
import java.math.BigDecimal
import org.json.JSONObject

@Composable
fun GetComposable(
  key: String,
  onDataChange: (String, Any, Boolean) -> Unit,
  imeAction: ImeAction,
  currJsonObject: JSONObject,
  level: Int,
  rootKey: String
) {
  when (typeOf(key, currJsonObject)) {
    FieldType.String -> DefaultTextField(
      label = key,
      value = currJsonObject.getString(key),
      onChange = { k, v -> onDataChange(k, v, false) },
      imeAction = imeAction,
      level = level
    )
    FieldType.Integer -> DefaultTextField(
      label = key,
      value = currJsonObject.getInt(key).toString(),
      onChange = { k, v ->
        onDataChange(k, v.parseInt(), false)
      },
      keyboardType = KeyboardType.Number,
      imeAction = imeAction,
      level = level
    )
    FieldType.Long -> DefaultTextField(
      label = key,
      value = currJsonObject.getLong(key).toString(),
      onChange = { k, v ->
        onDataChange(k, v.parseLong(), false)
      },
      keyboardType = KeyboardType.Number,
      imeAction = imeAction,
      level = level
    )
    FieldType.Double -> DefaultTextField(
      label = key,
      value = currJsonObject.getString(key),
      onChange = { k, v ->
        onDataChange(k, v.parseDouble(), false)
      },
      keyboardType = KeyboardType.Number,
      imeAction = imeAction,
      level = level
    )
    FieldType.Boolean -> DefaultSwitch(
      label = key,
      value = currJsonObject.getBoolean(key),
      onChange = { k, v -> onDataChange(k, v, false) },
      level = level
    )
    FieldType.TextFieldObject -> {
      val nestedJSONObject = currJsonObject.getJSONObject(key)
      val textFieldObject = TextFieldModelMapper.parseFromJson(key, nestedJSONObject)
      DefaultTextFieldWithError(
        label = textFieldObject.label,
        value = textFieldObject.value,
        onChange = { k, v -> onDataChange(k, v, true) },
        error = textFieldObject.error ?: "",
        keyboardType = getKeyboardTypeFromValue(KEY_VALUE, nestedJSONObject),
        imeAction = imeAction,
        level = level
      )
    }
    else -> {
      if (currJsonObject.get(key) is JSONObject) {
        FillJSONObjectWithComposables(
          onDataChange = { k, v -> onDataChange(k, v, false) },
          currJsonObject = currJsonObject.getJSONObject(key),
          level = level + 1,
          header = key,
          rootKey = rootKey.plus(".").plus(key)
        )
      } else {
        Text(text = currJsonObject.get(key).toString())
      }
    }
  }
}

private fun getKeyboardTypeFromValue(key: String, jsonObj: JSONObject): KeyboardType {
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

private fun isTextFieldObject(key: String, currJsonObject: JSONObject): Boolean {
  val nestedObject = currJsonObject.getJSONObject(key)
  return nestedObject.has("value")
}
