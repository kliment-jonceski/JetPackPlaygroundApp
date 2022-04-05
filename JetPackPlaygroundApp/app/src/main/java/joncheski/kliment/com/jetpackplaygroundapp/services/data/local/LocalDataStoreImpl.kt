package joncheski.kliment.com.jetpackplaygroundapp.services.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import joncheski.kliment.com.jetpackplaygroundapp.App
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LocalDataStoreImpl: LocalDataStore {
  companion object {
    private const val CUSTOM_TEXT_SETTINGS_KEY = "custom_text"
    private const val CUSTOM_TEXT_SETTINGS_DEFAULT_VALUE = "default text"
  }
  private val tag = LocalDataStoreImpl::class.java.simpleName
  private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settingsDataStore")
  private val customTextKey = stringPreferencesKey(CUSTOM_TEXT_SETTINGS_KEY)

  override val customTextData: Flow<String>
    get() = App.applicationContext().dataStore.data
      .map { preferences ->
        preferences[customTextKey] ?: CUSTOM_TEXT_SETTINGS_DEFAULT_VALUE
      }

  override suspend fun saveDefaultText(defaultText: String) {
    App.applicationContext().dataStore.edit { preferences ->
      preferences[customTextKey] = defaultText
    }
  }
}