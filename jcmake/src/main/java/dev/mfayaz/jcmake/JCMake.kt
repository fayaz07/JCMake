package dev.mfayaz.jcmake

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.mfayaz.jcmake.parser.JSONUIParser

@Composable
fun MakeUI(json: String, onDataChange: (String, String) -> Unit) {
  val jsonUiParser = JSONUIParser(json)

  LazyColumn(
    modifier = Modifier
      .fillMaxWidth()
      .padding(horizontal = 16.dp)
  ) {
    item {
      jsonUiParser.Fill(onDataChange)
    }
  }
}
