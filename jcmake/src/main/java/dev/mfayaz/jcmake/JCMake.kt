package dev.mfayaz.jcmake

import android.util.Log
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.mfayaz.jcmake.parser.JSONUIParser
import dev.mfayaz.jcmake.parser.ui.Fill

@Composable
fun MakeUI(modifier: Modifier, jsonUiParser: JSONUIParser, onDataChange: (String, Any) -> Unit ) {
  LazyColumn(
    modifier = modifier
      .fillMaxWidth()
      .padding(horizontal = 8.dp)
  ) {
    item {
      Fill(jsonUiParser.rootJsonObject, onDataChange)
    }
    item {
      Spacer(modifier = Modifier.padding(16.dp))
    }
  }
}
