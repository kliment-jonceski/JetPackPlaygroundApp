package joncheski.kliment.com.jetpackplaygroundapp.di

import joncheski.kliment.com.jetpackplaygroundapp.services.data.local.LocalDataStore
import joncheski.kliment.com.jetpackplaygroundapp.services.data.local.LocalDataStoreImpl
import org.koin.dsl.module

val dependenciesAppModule = module {
  single { LocalDataStoreImpl() as LocalDataStore }
}