package joncheski.kliment.com.jetpackplaygroundapp.ui.datastoreactivity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import joncheski.kliment.com.jetpackplaygroundapp.services.data.local.LocalDataStore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class DataStoreViewModel: ViewModel(), KoinComponent {
  private val tag = DataStoreViewModel::class.java.simpleName
  private val localDataStore: LocalDataStore by inject()
  private val mutableFlowData = MutableStateFlow(LatestDataUiState.Success(""))
  val uiStateFlow: StateFlow<LatestDataUiState> = mutableFlowData

  init {
    viewModelScope.launch {
      localDataStore.customTextData.collect { data ->
        mutableFlowData.value = LatestDataUiState.Success(data)
      }
    }
  }

  fun saveDefaultText(defaultText: String) {
    viewModelScope.launch {
      localDataStore.saveDefaultText(defaultText)
    }
  }
}

// Represents different states for the LatestNews screen
sealed class LatestDataUiState {
  data class Success(val tempString: String): LatestDataUiState()
  data class Error(val exception: Throwable): LatestDataUiState()
}