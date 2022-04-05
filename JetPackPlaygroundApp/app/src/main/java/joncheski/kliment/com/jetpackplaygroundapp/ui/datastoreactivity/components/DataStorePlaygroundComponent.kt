package joncheski.kliment.com.jetpackplaygroundapp.ui.datastoreactivity.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import joncheski.kliment.com.jetpackplaygroundapp.ui.datastoreactivity.ui.theme.JetPackPlaygroundAppTheme
import joncheski.kliment.com.jetpackplaygroundapp.ui.datastoreactivity.ui.theme.onPrimary

@ExperimentalComposeUiApi
@Composable
fun DataStorePlaygroundActivityComponent(
  stringData: String,
  keyboardController: SoftwareKeyboardController?,
  onTextChangeAction: (dataString: String) -> Unit
) {
  val textAnswerState = remember {
    mutableStateOf("")
  }
  JetPackPlaygroundAppTheme {
    // A surface container using the 'background' color from the theme
    Surface(color = MaterialTheme.colors.background) {
      Column(Modifier.fillMaxSize()) {
        Title("DataStore Playground")
        Spacer(modifier = Modifier.height(16.dp))
        NormalText("Data: $stringData")
        Spacer(modifier = Modifier.height(32.dp))
        TextFieldSingleLine(
          textAnswerState,
          "enter settings text",
          keyboardController,
          onTextChangeAction
        )
      }
    }
  }
}

@Composable
fun Title(text: String) {
  Text(
    text = text,
    modifier = Modifier
      .fillMaxWidth()
      .padding(horizontal = 8.dp, vertical = 8.dp),
    fontSize = 18.sp,
    textAlign = TextAlign.Center
  )
}

@Composable
fun NormalText(text: String) {
  Text(
    text = text,
    modifier = Modifier
      .fillMaxWidth()
      .padding(horizontal = 8.dp, vertical = 8.dp),
    fontSize = 16.sp,
    textAlign = TextAlign.Center
  )
}

@ExperimentalComposeUiApi
@Composable
fun TextFieldSingleLine(
  txtAnswerState: MutableState<String>,
  hintText: String,
  keyboardController: SoftwareKeyboardController?,
  onTextChangeAction: (dataString: String) -> Unit
) {
  TextField(
    textStyle = TextStyle.Default.copy(fontSize = 16.sp),
    value = txtAnswerState.value, onValueChange = {
      txtAnswerState.value = it
      onTextChangeAction(it)
    },
    label = {
      Text(text = hintText, fontSize = 14.sp)
    },
    modifier = Modifier.fillMaxWidth(),
    colors = TextFieldDefaults.textFieldColors(backgroundColor = onPrimary),
    singleLine = true,
    keyboardOptions = KeyboardOptions.Default.copy(
      capitalization = KeyboardCapitalization.None,
      autoCorrect = false,
      keyboardType = KeyboardType.Text,
      imeAction = ImeAction.Done
    ),
    keyboardActions = KeyboardActions(onDone = {
      keyboardController?.hide()
    })
  )
}

@ExperimentalComposeUiApi
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
  DataStorePlaygroundActivityComponent("preview data",
    LocalSoftwareKeyboardController.current,
    {})
}