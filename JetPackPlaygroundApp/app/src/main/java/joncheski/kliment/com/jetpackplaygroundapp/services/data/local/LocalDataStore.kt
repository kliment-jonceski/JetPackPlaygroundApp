package joncheski.kliment.com.jetpackplaygroundapp.services.data.local

import kotlinx.coroutines.flow.Flow

interface LocalDataStore {

  val customTextData: Flow<String>

  suspend fun saveDefaultText(defaultText: String)
}