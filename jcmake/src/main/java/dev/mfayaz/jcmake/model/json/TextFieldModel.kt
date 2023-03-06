package dev.mfayaz.jcmake.model.json

import dev.mfayaz.jcmake.model.ui.ComposableStyling

data class TextFieldModel(
  val value: String,
  val error: String? = "",
  val label: String? = "",
  val style: ComposableStyling
)
