package dev.mfayaz.jcmake.model.mapper

import dev.mfayaz.jcmake.model.json.KEY_ERROR
import dev.mfayaz.jcmake.model.json.KEY_VALUE
import dev.mfayaz.jcmake.model.json.TextFieldModel
import org.json.JSONObject

object TextFieldModelMapper {
  fun parseFromJson(key: String, json: JSONObject): TextFieldModel {
    return TextFieldModel(
      label = key,
      value = json.getString(KEY_VALUE),
      error = parseError(json)
    )
  }

  private fun parseError(json: JSONObject): String {
    return if (json.has(KEY_ERROR)) {
      json.getString(KEY_ERROR)
    } else {
      ""
    }
  }
}
