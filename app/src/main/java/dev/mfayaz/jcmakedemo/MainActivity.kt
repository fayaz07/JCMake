  package dev.mfayaz.jcmakedemo

  import android.os.Bundle
  import android.util.Log
  import androidx.activity.ComponentActivity
  import androidx.activity.compose.setContent
  import androidx.compose.foundation.layout.fillMaxSize
  import androidx.compose.material3.MaterialTheme
  import androidx.compose.material3.Surface
  import androidx.compose.runtime.Composable
  import androidx.compose.ui.Modifier
  import dev.mfayaz.jcmake.MakeUI2
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
            Content()
          }
        }
      }
    }
  }

  fun onDataChange(key: String, value: String) {
    Log.d("change", "key=$key, value=$value")
  }

  @Composable
  fun Content() {
    MakeUI2(TestData.simpleField, ::onDataChange)
  }
