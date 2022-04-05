package joncheski.kliment.com.jetpackplaygroundapp.ui.datastoreactivity

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import joncheski.kliment.com.jetpackplaygroundapp.ui.datastoreactivity.components.DataStorePlaygroundActivityComponent
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class DataStorePlaygroundActivity : ComponentActivity() {

  private var dataStoreViewModel: DataStoreViewModel? = null
  private val tag = DataStorePlaygroundActivity::class.java.simpleName

  @ExperimentalComposeUiApi
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    dataStoreViewModel = ViewModelProvider(this).get(DataStoreViewModel::class.java)

    lifecycleScope.launch {
      repeatOnLifecycle(Lifecycle.State.STARTED) {
        dataStoreViewModel?.uiStateFlow?.collect { uiState ->
          // New value received
          when (uiState) {
            is LatestDataUiState.Success -> showSuccessUI(uiState.tempString)
            is LatestDataUiState.Error -> showErrorUI(uiState.exception)
          }
        }
      }
    }
  }

  @ExperimentalComposeUiApi
  private fun showSuccessUI(stringData: String) {
    setContent {
      DataStorePlaygroundActivityComponent(
        stringData,
        LocalSoftwareKeyboardController.current
      ) { dataString ->
        dataStoreViewModel?.saveDefaultText(dataString)
      }
    }
  }

  @ExperimentalComposeUiApi
  private fun showErrorUI (exception: Throwable) {
    setContent {
      DataStorePlaygroundActivityComponent("Error loading data: ${exception.message}",
        null,
        {})
    }
  }
}