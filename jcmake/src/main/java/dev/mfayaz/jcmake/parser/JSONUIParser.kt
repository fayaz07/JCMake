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
  lateinit var rootJsonObject: JSONObject

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

}
