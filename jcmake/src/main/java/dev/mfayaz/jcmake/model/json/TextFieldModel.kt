package dev.mfayaz.jcmake.model.json

const val KEY_VALUE = "value"
const val KEY_ERROR = "error"
const val KEY_LABEL = "label"

data class TextFieldModel(
  val value: String,
  val error: String? = "",
  val label: String = ""
)
