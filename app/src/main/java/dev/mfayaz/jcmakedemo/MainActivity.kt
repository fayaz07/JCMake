package dev.mfayaz.jcmakedemo

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import dev.mfayaz.jcmake.MakeUI
import dev.mfayaz.jcmake.parser.JSONUIParser
import dev.mfayaz.jcmake.ui.PrettyJSON
import dev.mfayaz.jcmakedemo.data.TestData
import dev.mfayaz.jcmakedemo.ui.theme.JCMakeTheme

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      JCMakeTheme {
        // A surface container using the 'background' color from the theme
        Surface(
          modifier = Modifier.fillMaxSize(),
          color = MaterialTheme.colorScheme.background
        ) {
//          Content()
          PrettyJSONDemo()
        }
      }
    }
  }
}

fun onDataChange(key: String, value: Any) {
  Log.d("change", "key=$key, value=$value")
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Content() {
  val jsonUiParser = remember { JSONUIParser(TestData.nestedJsonLevel4) }
  Scaffold(
    topBar = {
      TopAppBar(
        title = { Text(text = "JCMake demo") },
        actions = {
          IconButton(onClick = {
            Log.d("json-print", jsonUiParser.rootJsonObject.toString())
          }) {
            Icon(Icons.Default.Send, "send")
          }
        }
      )
    }
  ) {
    MakeUI(
      modifier = Modifier.padding(it),
      jsonUiParser = jsonUiParser,
      onDataChange = ::onDataChange
    )
  }
}

@Composable
fun PrettyJSONDemo() {
  val jsonUiParser = remember { JSONUIParser(TestData.nestedJsonLevel4) }
  PrettyJSON(
    jsonUiParser = jsonUiParser
  )
}
