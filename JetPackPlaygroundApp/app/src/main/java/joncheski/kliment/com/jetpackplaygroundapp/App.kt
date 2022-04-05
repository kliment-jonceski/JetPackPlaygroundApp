package joncheski.kliment.com.jetpackplaygroundapp

import android.app.Application
import android.content.Context
import joncheski.kliment.com.jetpackplaygroundapp.di.dependenciesAppModule
import org.koin.core.component.KoinComponent
import org.koin.core.context.startKoin

class App: Application(), KoinComponent {
  companion object {
    private var instance: App? = null

    fun applicationContext() : Context {
      return instance!!.applicationContext
    }
  }

  init {
    instance = this
  }

  override fun onCreate() {
    super.onCreate()
    startKoin {
      // use Koin logger
      printLogger()
      // declare modules
      modules(dependenciesAppModule)
    }
  }
}